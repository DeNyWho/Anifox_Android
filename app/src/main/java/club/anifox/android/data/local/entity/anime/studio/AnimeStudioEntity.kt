package club.anifox.android.data.local.entity.anime.studio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_studio")
data class AnimeStudioEntity(
    @PrimaryKey val id: String,
    val name: String
)
