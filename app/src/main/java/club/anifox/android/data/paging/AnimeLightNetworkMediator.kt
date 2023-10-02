package club.anifox.android.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import club.anifox.android.data.local.cache.entity.content.AnimeLightEntity
import club.anifox.android.data.local.cache.entity.content.AnimeLightRemoteKeyEntity
import club.anifox.android.data.local.database.AniFoxDataBase
import club.anifox.android.data.local.mappers.toAnimeLight
import club.anifox.android.data.local.mappers.toAnimeLightEntity
import club.anifox.android.data.remote.anime.AnimeService
import club.anifox.android.domain.model.common.Resource
import club.anifox.android.domain.state.StateListWrapper

@OptIn(ExperimentalPagingApi::class)
class AnimeLightNetworkMediator(
    private val animeService: AnimeService,
    private val anifoxLocal: AniFoxDataBase,
    private val season: String? = null,
    private val ratingMpa: String? = null,
    private val minimalAge: String? = null,
    private val type: String? = null,
    private val order: String? = null,
    private val pageSize: Int = 24,
    private val status: String? = null,
    private val genres: List<String>? = null,
    private val searchQuery: String? = null,
    private val year: Int? = null,
) : RemoteMediator<Int, AnimeLightEntity>() {

    private val animeLightDao = anifoxLocal.animeLightDao()
    private val animeLightRemoteKeyDao = anifoxLocal.animeLightRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeLightEntity>,
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 0
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null,
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null,
                        )
                    nextPage
                }
            }

            val animeResponse = animeService.getAnime(
                order = order,
                pageNum = currentPage,
                pageSize = pageSize,
                status = status,
                genres = genres,
                searchQuery = searchQuery,
                minimalAge = minimalAge,
                ratingMpa = ratingMpa,
                season = season,
                type = type,
                year = year,
            )

            val animeResult = when (animeResponse) {
                is Resource.Success -> {
                    val data = animeResponse.data.map { it.toAnimeLight() }
                    StateListWrapper(data)
                }

                is Resource.Error -> {
                    StateListWrapper(error = animeResponse.error)
                }

                is Resource.Loading -> {
                    StateListWrapper.loading()
                }
            }

            val endOfPaginationReached = animeResult.data?.isEmpty()

            val prevPage = if (currentPage == 0) null else currentPage - 1
            val nextPage = if (endOfPaginationReached == true) null else currentPage + 1

            anifoxLocal.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    animeLightDao.clearAll()
                    animeLightRemoteKeyDao.deleteAllRemoteKeys()
                }

                val keys = animeResult.data?.map { animeLight ->
                    AnimeLightRemoteKeyEntity(
                        url = animeLight.url,
                        prevPage = prevPage,
                        nextPage = nextPage,
                    )
                }

                if (keys != null) {
                    animeLightRemoteKeyDao.addAllRemoteKeys(remoteKeys = keys)
                }
                animeResult.data?.map { it.toAnimeLightEntity() }
                    ?.let { animeLightDao.insertAll(animeLights = it) }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, AnimeLightEntity>,
    ): AnimeLightRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                animeLightRemoteKeyDao.getRemoteKey(url)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, AnimeLightEntity>,
    ): AnimeLightRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { animeLightEntity ->
                animeLightRemoteKeyDao.getRemoteKey(animeLightEntity.url)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, AnimeLightEntity>,
    ): AnimeLightRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { animeLightEntity ->
                animeLightRemoteKeyDao.getRemoteKey(animeLightEntity.url)
            }
    }
}
