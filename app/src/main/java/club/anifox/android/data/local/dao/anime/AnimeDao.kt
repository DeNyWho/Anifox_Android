package club.anifox.android.data.local.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import club.anifox.android.data.local.entity.anime.AnimeEntity
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity
import club.anifox.android.data.local.entity.anime.relation.AnimeAndImage

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime WHERE :animeUrl = url")
    suspend fun findByUrl(animeUrl: String): AnimeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: AnimeImageEntity)

    @Transaction
    @Query("SELECT * FROM anime where :animeUrl = url")
    suspend fun getAnime(animeUrl: String): AnimeAndImage

    @Query("DELETE FROM anime WHERE :animeUrl = url")
    suspend fun delete(animeUrl: String)

    @Query("DELETE FROM anime")
    suspend fun deleteAll()
}