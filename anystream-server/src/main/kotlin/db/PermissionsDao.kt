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

import anystream.db.model.PermissionDb
import anystream.models.InviteCode
import org.jdbi.v3.sqlobject.customizer.BindList
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@UseClasspathSqlLocator
interface PermissionsDao {

    @SqlUpdate
    fun createTable()

    @SqlQuery("SELECT * FROM permissions WHERE userId = ?")
    fun allForUser(userId: Int): List<PermissionDb>

    @SqlQuery("SELECT * FROM permissions WHERE userId = ? AND name = ?")
    fun find(userId: Int, name: String): PermissionDb?

    @SqlUpdate("DELETE FROM permissions WHERE userId = ? AND name = ?")
    fun delete(userId: Int, name: String)

    @SqlUpdate("DELETE FROM permissions WHERE userId = ?")
    fun deleteAllForUser(userId: Int)

    @SqlUpdate("INSERT INTO permissions (userId, name) VALUES (:userId, <permissions>)")
    fun insertPermissions(
        userId: Int,
        @BindList("permissions") permissions: Set<String>
    )
}