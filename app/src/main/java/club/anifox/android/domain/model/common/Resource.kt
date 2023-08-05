package club.anifox.android.domain.model.common

sealed class Resource {

    data class Success<T>(
        val data: T
    ) : Resource()

    data class Error(
        val error: ApiError
    ) : Resource()

}

data class ApiError(
    val statusCode: Int,
    val message: String
)