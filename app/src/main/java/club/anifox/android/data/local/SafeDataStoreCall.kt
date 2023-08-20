package club.anifox.android.data.local

import club.anifox.android.domain.model.common.ApiError
import club.anifox.android.domain.model.common.Resource
import java.io.IOException

suspend inline fun <reified T : Any> safeDataStoreCall(
    crossinline call: suspend () -> T?
): Resource<T> {
    return try {
        val result = call()

        if (result != null) {
            Resource.Success(data = result)
        } else {
            Resource.Error(ApiError(404, "Data not found"))
        }
    } catch (e: IOException) {
        Resource.Error(ApiError(500, e.message ?: "Unknown error"))
    }
}