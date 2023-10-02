package club.anifox.android.domain.model.common

import io.ktor.http.Cookie

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()

    data class Success<out T>(val data: T, val cookies: List<Cookie>? = null) : Resource<T>()

    data class Error(
        val error: ApiError,
    ) : Resource<Nothing>()
}

data class ApiError(
    val statusCode: Int = 200,
    val message: String = "",
)
