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
package anystream.db.model

import anystream.models.PlaybackState

data class PlaybackStateDb(
    val id: Int,
    val mediaReferenceId: Int,
    val mediaId: Int,
    val userId: Int,
    val position: Double,
    val runtime: Double,
    val updatedAt: Long,
) {
    companion object {
        fun from(state: PlaybackState): PlaybackStateDb {
            return PlaybackStateDb(
                id = state.id,
                mediaReferenceId = state.mediaReferenceId,
                mediaId = state.mediaId,
                userId = state.userId,
                position = state.position,
                runtime = state.runtime,
                updatedAt = state.updatedAt,
            )
        }
    }

    fun toStateModel(): PlaybackState {
        return PlaybackState(
            id = id,
            mediaReferenceId = mediaReferenceId,
            mediaId = mediaId,
            userId = userId,
            position = position,
            runtime = runtime,
            updatedAt = updatedAt,
        )
    }
}
