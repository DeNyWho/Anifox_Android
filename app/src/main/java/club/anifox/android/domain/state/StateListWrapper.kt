package club.anifox.android.domain.state

import club.anifox.android.domain.model.common.Resource

data class StateListWrapper<T>(
    override val isLoading: Boolean = false,
    override val result: Resource = Resource.Success(null),
    val data: List<T> = emptyList()
) : BaseState<List<T>>