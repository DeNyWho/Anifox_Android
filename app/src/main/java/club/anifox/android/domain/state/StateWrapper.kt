package club.anifox.android.domain.state

import club.anifox.android.domain.model.common.Resource

data class StateWrapper<T>(
    override val isLoading: Boolean = false,
    override val result: Resource = Resource.Success(null),
    val data: T? = null
) : BaseState<T>