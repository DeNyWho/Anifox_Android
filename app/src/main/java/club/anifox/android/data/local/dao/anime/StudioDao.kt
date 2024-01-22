package club.anifox.android.data.local.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import club.anifox.android.data.local.entity.anime.studio.AnimeStudioEntity

@Dao
interface StudioDao {
    @Query("SELECT * FROM anime_studio")
    suspend fun getAllStudios(): List<AnimeStudioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudios(studios: List<AnimeStudioEntity>)

    @Query("DELETE FROM anime_studio")
    suspend fun deleteAllStudios()
}
