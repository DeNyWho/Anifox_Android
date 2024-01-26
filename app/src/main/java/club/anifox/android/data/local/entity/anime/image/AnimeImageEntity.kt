package club.anifox.android.data.local.entity.anime.image

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.anifox.android.domain.model.anime.image.AnimeImage

@Entity(tableName = "anime_image")
data class AnimeImageEntity(
    @PrimaryKey val animeUrl: String = "",
    val large: String = "",
    val medium: String = "",
)

fun AnimeImageEntity.toImage() = AnimeImage(
    large = large,
    medium = medium,
)
