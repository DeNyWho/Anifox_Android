package club.anifox.android.domain.model.common

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T) : Resource<T>()

    data class Error(
        val error: ApiError
    ) : Resource<Nothing>()

}

data class ApiError(
    val statusCode: Int = 200,
    val message: String = ""
)