package club.anifox.android.data.local.entity.anime.studio

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.anifox.android.domain.model.anime.studio.AnimeStudio

@Entity(tableName = "anime_studio")
data class AnimeStudioEntity(
    @PrimaryKey val studioId: String = "",
    val name: String = "",
)

fun AnimeStudioEntity.asStudio() = AnimeStudio(
    id = studioId,
    name = name,
)