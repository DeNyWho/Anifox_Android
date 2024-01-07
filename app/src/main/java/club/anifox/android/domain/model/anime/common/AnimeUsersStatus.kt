package club.anifox.android.domain.model.anime.common

import kotlinx.serialization.Serializable

@Serializable
data class AnimeUsersStatus(
    val watching: Int = 0,
    val inPlan: Int = 0,
    val watched: Int = 0,
    val postponed: Int = 0,
)

fun AnimeUsersStatus.isAllZero() =
    watching == 0 && watched == 0 && inPlan == 0 && postponed == 0
