package club.anifox.android.core

import club.anifox.android.BuildConfig

object Endpoints {
    const val domain = BuildConfig.hostname
    const val api = BuildConfig.api_path
    const val anime = "/anime/"
    const val screenShots = "/screenshots"
    const val related = "/related"
    const val similar = "/similar"
    const val status = "/status"
    const val media = "/media"
    const val genres = "/genres"

    const val account = "/account"
    const val nickname = "/nickname"

    const val auth = "/auth"
    const val refresh = "/refreshToken"
    const val authentication = "/authentication"
}
