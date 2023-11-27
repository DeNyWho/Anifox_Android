package club.anifox.android.data.local.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import club.anifox.android.data.local.entity.anime.AnimeEntity

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime WHERE :animeUrl = url")
    suspend fun findById(animeUrl: String): AnimeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Query("DELETE FROM anime WHERE :animeUrl = url")
    suspend fun delete(animeUrl: Int)

    @Query("DELETE FROM anime")
    suspend fun deleteAll()
}