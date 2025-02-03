package ru.kpfu.minn.feature.profile.impl.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.QuerySnapshot
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository

internal class FavoritesPagingSource @AssistedInject constructor(
    @Assisted private val userId: String?,
    private val favoritesRepository: FavoritesRepository,
): PagingSource<QuerySnapshot, ImageUrlDomainModel>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, ImageUrlDomainModel>): QuerySnapshot? = null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, ImageUrlDomainModel> {
        return try {
            val currentPage = params.key ?: favoritesRepository
                .getFavorites(userId)
                .get()
                .await()
            val lastVisibleProduct = currentPage.documents.lastOrNull()
            val nextPage = lastVisibleProduct?.let {
                favoritesRepository
                    .getFavorites(userId)
                    .startAfter(lastVisibleProduct)
                    .get()
                    .await()
            }
            LoadResult.Page(
                data = currentPage.toObjects(ImageUrlDomainModel::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface FavoritesPagingSourceFactory {
        fun create(@Assisted userId: String?): FavoritesPagingSource
    }
}