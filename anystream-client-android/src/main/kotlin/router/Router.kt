/**
 * AnyStream
 * Copyright (C) 2021 Drew Carlson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package anystream.android.router

import androidx.compose.runtime.*

private fun key(backStackIndex: Int) =
    "K$backStackIndex"

private val backStackMap: MutableMap<Any, BackStack<*>> =
    mutableMapOf()

val LocalRouting: ProvidableCompositionLocal<List<Any>> = compositionLocalOf {
    listOf<Any>()
}

@Composable
inline fun <reified T> Router(
    defaultRouting: T,
    noinline children: @Composable (BackStack<T>) -> Unit
) {
    Router(T::class.java.name, defaultRouting, children)
}

@Composable
fun <T> Router(
    contextId: String,
    defaultRouting: T,
    children: @Composable (BackStack<T>) -> Unit
) {
    val route = LocalRouting.current
    val routingFromAmbient = route.firstOrNull() as? T
    val downStreamRoute = if (route.size > 1) route.takeLast(route.size - 1) else emptyList()

    val upstreamHandler = LocalBackPressHandler.current
    val localHandler = remember { BackPressHandler("${upstreamHandler.id}.$contextId") }
    val backStack = fetchBackStack(localHandler.id, defaultRouting, routingFromAmbient)
    val handleBackPressHere: () -> Boolean = { localHandler.handle() || backStack.pop() }

    SideEffect {
        upstreamHandler.children.add(handleBackPressHere)
    }
    DisposableEffect(Unit) {
        onDispose {
            upstreamHandler.children.remove(handleBackPressHere)
        }
    }

    @Composable
    fun Observe(body: @Composable () -> Unit) = body()

    Observe {
        // Not recomposing router on backstack operation
        BundleScope(key(backStack.lastIndex), autoDispose = false) {
            CompositionLocalProvider(
                LocalBackPressHandler provides localHandler,
                LocalRouting provides downStreamRoute
            ) {
                children(backStack)
            }
        }
    }
}

@Composable
private fun <T> fetchBackStack(key: String, defaultElement: T, override: T?): BackStack<T> {
    val upstreamBundle = LocalSavedInstanceState.current
    val onElementRemoved: (Int) -> Unit = { upstreamBundle.remove(key(it)) }

    @Suppress("UNCHECKED_CAST")
    val existing = backStackMap[key] as BackStack<T>?
    @Suppress("UNCHECKED_CAST")
    return when {
        override != null -> BackStack(override as T, onElementRemoved)
        existing != null -> existing
        else -> BackStack(defaultElement, onElementRemoved)
    }.also {
        backStackMap[key] = it
    }
}

