package club.anifox.android.data.local.cache.entity.content

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_remote_keys")
data class AnimeLightRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String = "",
    val prevPage: Int?,
    val nextPage: Int?,
)
