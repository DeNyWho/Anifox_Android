package club.anifox.android.domain.state

import club.anifox.android.domain.model.common.Resource

data class StateMapWrapper<K, V>(
    override val isLoading: Boolean = false,
    override val result: Resource = Resource.Success(null),
    val data: Map<K, V> = emptyMap()
) : BaseState<Map<K, V>>