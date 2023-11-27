package club.anifox.android.data.local.entity.anime.image

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_image")
data class AnimeImageEntity(
    @PrimaryKey val animeUrl: String,
    val large: String,
    val medium: String
)
