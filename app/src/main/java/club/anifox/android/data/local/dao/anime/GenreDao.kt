package club.anifox.android.data.local.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import club.anifox.android.data.local.entity.anime.genres.AnimeGenresEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM anime_genres")
    suspend fun getAllGenres(): List<AnimeGenresEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<AnimeGenresEntity>)

    @Query("DELETE FROM anime_genres")
    suspend fun deleteAllGenres()
}
