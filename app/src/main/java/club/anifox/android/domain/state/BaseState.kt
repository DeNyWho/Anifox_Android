package club.anifox.android.domain.state

import club.anifox.android.domain.model.common.Resource

interface BaseState<T> {

    val isLoading: Boolean
    val result: Resource

    companion object {

        fun <T> loading() = object : BaseState<T> {
            override val isLoading: Boolean = true
            override val result: Resource = Resource.Success(null)
        }

    }

}