package club.anifox.android.domain.state

import club.anifox.android.domain.model.common.Resource

data class StateWrapper<T>(
    val isLoading: Boolean = false,
    val result: Resource<T> = Resource.Loading
) {
    companion object {
        inline fun <reified T> loading(): StateWrapper<T> {
            return StateWrapper(
                isLoading = true,
                result = Resource.Loading
            )
        }
    }
}