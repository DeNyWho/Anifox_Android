package club.anifox.android.data.local.cache.dao.content

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import club.anifox.android.data.local.cache.entity.content.AnimeLightRemoteKeyEntity

@Dao
interface AnimeLightRemoteKeyDao {

    @Query("Select * from anime_remote_keys where url = :url")
    suspend fun getRemoteKey(url: String): AnimeLightRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<AnimeLightRemoteKeyEntity>)

    @Query("Delete from anime_remote_keys")
    suspend fun deleteAllRemoteKeys()
}
