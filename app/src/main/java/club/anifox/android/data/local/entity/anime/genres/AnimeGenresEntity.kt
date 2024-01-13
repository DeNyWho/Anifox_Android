package club.anifox.android.data.local.entity.anime.genres

import androidx.room.Entity
import androidx.room.PrimaryKey
import club.anifox.android.domain.model.anime.genres.AnimeGenre

@Entity(tableName = "anime_genres")
data class AnimeGenresEntity(
    @PrimaryKey val genreId: String = "",
    val name: String = "",
)

fun AnimeGenresEntity.asGenre() = AnimeGenre(
    id = genreId,
    name = name,
)