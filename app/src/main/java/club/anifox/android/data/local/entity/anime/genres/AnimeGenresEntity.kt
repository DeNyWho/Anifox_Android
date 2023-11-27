package club.anifox.android.data.local.entity.anime.genres

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_genres")
data class AnimeGenresEntity(
    @PrimaryKey val id: String,
    val name: String
)
