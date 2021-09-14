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
package anystream.frontend.components

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Img


@Composable
fun PosterCard(
    title: (@Composable () -> Unit)?,
    posterPath: String?,
    wide: Boolean = false,
    heightAndWidth: Pair<CSSpxValue, CSSpxValue>? = null,
    isAdded: Boolean? = null,
    completedPercent: Float? = null,
    subtitle1: (@Composable () -> Unit)? = null,
    subtitle2: (@Composable () -> Unit)? = null,
    onPlayClicked: (() -> Unit)? = null,
    onBodyClicked: (() -> Unit)? = null,
    buildMenu: @Composable (() -> Unit)? = null,
    classes: List<String>? = null,
) {
    val isMouseOver = remember { mutableStateOf(false) }
    val isMenuVisible = remember { mutableStateOf(false) }
    Div({
        if (classes == null) {
            classes("p-3")
        } else {
            classes(*classes.toTypedArray())
        }
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
        }
    }) {
        val (posterHeight, posterWidth) = heightAndWidth ?: if (wide) {
            166.px to 250.px
        } else {
            250.px to 166.px
        }

        Div({
            classes("card", "movie-card", "border-0", "shadow")
            onMouseEnter {
                isMouseOver.value = true
                it.stopPropagation()
            }
            onMouseLeave {
                isMouseOver.value = false
                it.stopPropagation()
            }
            style {
                backgroundColor(Color.darkgray)
                height(posterHeight)
                width(posterWidth)
            }
        }) {
            val isOverlayVisible by derivedStateOf {
                isMouseOver.value || isMenuVisible.value
            }

            CardOverlay(
                isAdded = isAdded,
                onPlayClicked = onPlayClicked,
                onBodyClicked = onBodyClicked,
                isOverlayVisible = isOverlayVisible,
                onMenuClicked = { isMenuVisible.value = true }
                    .takeUnless { buildMenu == null }
            )

            if (completedPercent != null) {
                val progressBarHeight = 5.px
                Div({
                    classes("w-100", "rounded-bottom")
                    style {
                        position(Position.Absolute)
                        height(progressBarHeight)
                        bottom(0.px)
                        backgroundColor(rgba(0, 0, 0, .6))
                        property("transition", "opacity 0.15s ease-in-out 0s;")
                        if (isOverlayVisible) {
                            opacity(0)
                        }
                    }
                }) {
                    Div({
                        style {
                            position(Position.Absolute)
                            height(progressBarHeight)
                            backgroundColor(rgb(255, 8, 28))
                            width((completedPercent * 100).toInt().percent)
                            val roundingRadius = .25.cssRem
                            borderRadius(
                                topLeft = 0.px,
                                topRight = 0.px,
                                bottomLeft = roundingRadius,
                                bottomRight = if (completedPercent <= .98) 0.px else roundingRadius,
                            )
                        }
                    })
                }
            }

            if (!posterPath.isNullOrBlank()) {
                Img(
                    src = "https://image.tmdb.org/t/p/w300${posterPath}",
                    attrs = {
                        classes("rounded", "h-100", "w-100")
                        attr("loading", "lazy")
                    }
                )
            }
        }

        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(posterWidth)
                overflow("hidden")
                whiteSpace("nowrap")
            }
        }) {
            title?.invoke()
            subtitle1?.invoke()
            subtitle2?.invoke()
        }
    }
}

@Composable
private fun CardOverlay(
    isAdded: Boolean?,
    isOverlayVisible: Boolean,
    onPlayClicked: (() -> Unit)? = null,
    onBodyClicked: (() -> Unit)? = null,
    onMenuClicked: (() -> Unit)? = null,
) {
    Div({
        classes("rounded", "h-100", "w-100")
        style {
            position(Position.Absolute)
            property("cursor", "pointer")
            property("z-index", 1)
            opacity(if (isOverlayVisible) 1 else 0)
            property("transition", "opacity 0.15s ease-in-out")
            onClick {
                it.stopPropagation()
                onBodyClicked?.invoke()
            }
        }
    }) {
        Div({
            classes("rounded", "border", "h-100", "w-100", "p-3", "border-white")
            style {
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.SpaceBetween)
                alignItems(AlignItems.Center)
                flexDirection(FlexDirection.Column)
                property("z-index", 3)
                position(Position.Absolute)
            }
        }) {
            val isPlaySelected = remember { mutableStateOf(onPlayClicked == null) }
            I({
                classes("bi", "bi-three-dots-vertical")
                style {
                    fontSize(22.px)
                    display(DisplayStyle.Flex)
                    alignSelf(AlignSelf.FlexEnd)
                    color(rgb(255, 255, 255))
                    if (onMenuClicked == null) {
                        opacity(0)
                    } else {
                        onClick {
                            it.stopPropagation()
                            onMenuClicked()
                        }
                    }
                }
            })
            Div({
                style {
                    display(DisplayStyle.Flex)
                }
                if (onPlayClicked != null) {
                    onMouseEnter {
                        isPlaySelected.value = true
                        it.stopPropagation()
                    }
                    onMouseLeave {
                        isPlaySelected.value = false
                        it.stopPropagation()
                    }
                    onClick {
                        it.stopPropagation()
                        onPlayClicked()
                    }
                }
            }) {
                I({
                    classes("bi", if (isPlaySelected.value) "bi-play-circle-fill" else "bi-play-circle")
                    style {
                        property("margin", auto)
                        fontSize(48.px)
                        color(rgb(255, 255, 255))
                    }
                })
            }
            Div({
                style {
                    display(DisplayStyle.Flex)
                    alignSelf(AlignSelf.FlexEnd)
                }
            }) {
                I({
                    classes("bi", if (isAdded == true) "bi-check-lg" else "bi-plus-lg")
                    style {
                        color(rgb(255, 255, 255))
                        if (isAdded == null) {
                            opacity(0)
                        }
                    }
                })
            }
        }

        Div({
            classes("rounded", "h-100", "w-100")
            style {
                backgroundColor(rgb(0, 0, 0))
                position(Position.Absolute)
                property("z-index", 2)
                opacity(.7)
            }
        })
    }
}