package club.anifox.android.data.local.cache.dao.content

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import club.anifox.android.data.local.cache.entity.content.SearchHistoryEntity


@Dao
interface SearchHistoryDao {

    @Upsert
    suspend fun insertAll(searchHistory: List<SearchHistoryEntity>)

    @Query("Delete from searchHistory")
    suspend fun clearAll()
}