package club.anifox.android.data.local.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import club.anifox.android.data.local.entity.anime.AnimeEntity
import club.anifox.android.data.local.entity.anime.image.AnimeImageEntity
import club.anifox.android.data.local.entity.anime.relation.AnimeAndImage
import club.anifox.android.domain.model.anime.AnimeSeason
import club.anifox.android.domain.model.anime.AnimeStatus
import club.anifox.android.domain.model.anime.AnimeType

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime WHERE :animeUrl = url")
    suspend fun findByUrl(animeUrl: String): AnimeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: AnimeImageEntity)

    @Transaction
    @Query(
        "SELECT * FROM anime a where " +
        "(:status is null or :status = a.status) and" +
        "(:season is null or :season = a.season) and" +
        "(:type is null or :type = a.type) and" +
        "(:ratingMpa is null or :ratingMpa = a.rating) and" +
        "(:minimalAge is null or :minimalAge = a.minimalAge) and" +
        "(:year is null or :year = a.year)"
    )
    suspend fun getAnime(
        status: AnimeStatus?,
        season: AnimeSeason?,
        type: AnimeType?,
        ratingMpa: String?,
        minimalAge: Int?,
        year: Int?,
    ): List<AnimeAndImage>

    @Transaction
    @Query("SELECT * FROM anime a where a.url = :url")
    suspend fun getAnimeDetails(url: String): AnimeAndImage?

    @Query("DELETE FROM anime WHERE :animeUrl = url")
    suspend fun delete(animeUrl: String)

    @Query("DELETE FROM anime")
    suspend fun deleteAll()
}
