package club.anifox.android.data.remote.user

import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent

class UserService(
    private val client: HttpClient,
) : KoinComponent
