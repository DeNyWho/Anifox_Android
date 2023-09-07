package club.anifox.android.data.local.cache.entity.content

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("searchHistory")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val pk: String = UUID.randomUUID().toString(),
    val url: String,
    val title: String,
    val image: String
)