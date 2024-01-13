package club.anifox.android.data.local.entity.anime.studio

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "anime_studio_cross_ref",
    primaryKeys = ["animeUrl", "studioId"],
    indices = [Index("studioId")],
)
data class AnimeStudioCrossRef(
    val animeUrl: String,
    val studioId: String,
)