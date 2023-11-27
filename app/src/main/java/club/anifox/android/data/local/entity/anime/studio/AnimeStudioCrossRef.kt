package club.anifox.android.data.local.entity.anime.studio

import androidx.room.Entity

@Entity(tableName = "anime_studio_cross_ref", primaryKeys = ["animeUrl", "studioId"])
data class AnimeStudioCrossRef(
    val animeUrl: String,
    val studioId: String
)