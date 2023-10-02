package club.anifox.android.data.local.cache.entity.content

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("animeLight")
data class AnimeLightEntity(
    @PrimaryKey(autoGenerate = false)
    val pk: String,
    val url: String,
    val title: String,
    val image: String,
)
