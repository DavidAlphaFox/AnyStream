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
package anystream

import anystream.data.UserSession
import anystream.db.SessionsDao
import anystream.routes.installRouting
import anystream.util.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.CachingOptions
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.sessions.*
import io.ktor.util.date.GMTDate
import io.ktor.websocket.*
import kotlinx.serialization.json.Json
import org.bouncycastle.util.encoders.Hex
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.JdbiException
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.statement.Slf4JSqlLogger
import org.jdbi.v3.sqlite3.SQLitePlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.attach
import org.slf4j.event.Level
import java.time.Duration
import kotlin.random.Random
import kotlin.system.exitProcess

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    encodeDefaults = true
    allowStructuredMapKeys = true
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val databaseUrl = environment.config.property("app.databaseUrl").getString()

    val jdbi = Jdbi.create("jdbc:$databaseUrl").apply {
        setSqlLogger(Slf4JSqlLogger())
        installPlugin(SQLitePlugin())
        installPlugin(SqlObjectPlugin())
        installPlugin(KotlinSqlObjectPlugin())
        installPlugin(KotlinPlugin())
    }
    val dbHandle = try {
        jdbi.open()
    } catch (e: JdbiException) {
        log.error("failed to create database connection", e)
        exitProcess(-1)
    }

    val sessionsDao = dbHandle.attach<SessionsDao>().apply { createTable() }
    val sessionStorage = MongoSessionStorage(sessionsDao)

    install(DefaultHeaders) {}
    install(ContentNegotiation) { json(json) }
    install(AutoHeadResponse)
    install(ConditionalHeaders)
    install(PartialContent)
    //install(ForwardedHeaderSupport) WARNING: for security, do not include this if not behind a reverse proxy
    //install(XForwardedHeaderSupport) WARNING: for security, do not include this if not behind a reverse proxy
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024)
        }
        excludeContentType(ContentType.Video.Any)
    }

    install(CORS) {
        methods.addAll(HttpMethod.DefaultMethods)
        allowCredentials = true
        allowNonSimpleContentTypes = true
        allowHeaders { true }
        exposeHeader(UserSession.KEY)
        anyHost()
    }

    install(CallLogging) {
        level = Level.TRACE
    }

    install(CachingHeaders) {
        options { outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS -> CachingOptions(
                    CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60),
                    expires = null as? GMTDate?
                )
                else -> null
            }
        }
    }

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(60)
        timeout = Duration.ofSeconds(15)
    }

    install(Authentication) {
        session<UserSession> {
            challenge { context.respond(Unauthorized) }
            validate { it }
        }
    }
    install(Sessions) {
        header<UserSession>(UserSession.KEY, sessionStorage) {
            identity { Hex.toHexString(Random.nextBytes(48)) }
            serializer = MongoSessionStorage.Serializer
        }
    }
    install(WebsocketAuthorization) {
        extractUserSession(sessionStorage::readSession)
    }
    install(PermissionAuthorization) {
        extract { (it as UserSession).permissions }
    }
    installRouting(dbHandle)
}
