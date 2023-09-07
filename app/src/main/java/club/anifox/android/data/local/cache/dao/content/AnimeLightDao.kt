package club.anifox.android.data.local.cache.dao.content

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity

@Dao
interface AnimeLightDao {

    @Upsert
    suspend fun insertAll(animeLights: List<AnimeLightEntity>)

    @Query("Select * from animeLight")
    fun getAnimeLights(): PagingSource<Int, AnimeLightEntity>

    @Query("Select * from animeLight where url like :url")
    suspend fun getAnimeLight(url: String): AnimeLightEntity

    @Query("Delete from animeLight")
    suspend fun clearAll()
}