package ru.kpfu.minn.feature.search.impl.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.api.repository.UserRepository

internal class UserPagingSource @AssistedInject constructor(
    private val userRepository: UserRepository,
    @Assisted private val searchQuery: String,
): PagingSource<Long, UserDomainModel>() {
    override fun getRefreshKey(state: PagingState<Long, UserDomainModel>): Long? {
        return null
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, UserDomainModel> {
        return try {
            val currentKey = params.key ?: 0
            val pageSize = params.loadSize
            val result = userRepository.fetchUsers(
                page = currentKey,
                pageSize = pageSize,
                searchQuery = searchQuery,
            )
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if (result.size < pageSize) null else currentKey + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted searchQuery: String): UserPagingSource
    }
}