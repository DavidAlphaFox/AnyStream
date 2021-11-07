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
package anystream.db

import anystream.util.SessionData
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@UseClasspathSqlLocator
interface SessionsDao {

    @SqlUpdate
    fun createTable()

    @SqlQuery("SELECT * FROM sessions")
    fun all(): List<SessionData>

    @SqlUpdate("SELECT * FROM sessions WHERE id = ?")
    fun find(id: String): ByteArray?

    @SqlUpdate("INSERT INTO sessions (id, data) VALUES (?, ?) ON DUPLICATE KEY UPDATE data = :data")
    fun insertOrUpdate(id: String, data: ByteArray)

    @SqlUpdate("DELETE FROM sessions WHERE id = ?")
    fun delete(id: String)
}