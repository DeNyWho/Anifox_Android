package club.anifox.android.domain.model.dto.anime

import club.anifox.android.domain.model.anime.AnimeUsersStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeUsersStatusDto(
    @SerialName("Watching")
    val watching: Int? = null,
    @SerialName("InPlan")
    val inPlan: Int? = null,
    @SerialName("Watched")
    val watched: Int? = null,
    @SerialName("Postponed")
    val postponed: Int? = null,
)

fun AnimeUsersStatusDto.toAnimeUsersStatus(): AnimeUsersStatus {
    return AnimeUsersStatus(
        watching = watching ?: 0,
        inPlan = inPlan ?: 0,
        watched = watched ?: 0,
        postponed = postponed ?: 0
    )
}