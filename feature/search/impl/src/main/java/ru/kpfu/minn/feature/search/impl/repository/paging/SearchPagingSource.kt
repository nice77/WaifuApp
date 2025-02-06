package ru.kpfu.minn.feature.search.impl.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository

internal class SearchPagingSource @AssistedInject constructor(
    @Assisted private val tags: List<TagDomainModel>,
    private val waifuImagesRepository: WaifuImagesRepository,
): PagingSource<Long, ImageDomainModel>() {

    override fun getRefreshKey(state: PagingState<Long, ImageDomainModel>): Long? =
        null

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ImageDomainModel> {
        return try {
            val currentPage = waifuImagesRepository.fetchImages(tags)
            val currentKey = params.key ?: 0
            val nextKey = currentKey + 1
            LoadResult.Page(
                data = currentPage,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted tags: List<TagDomainModel>): SearchPagingSource
    }
}