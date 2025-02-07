package ru.kpfu.minn.feature.profile.impl.repository

import com.google.firebase.firestore.Query
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.impl.utils.toDataModel
import javax.inject.Inject

internal class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDatasource: FavoritesDatasource,
): FavoritesRepository {
    override suspend fun getFavorites(userId: String?): Query {
        return favoritesDatasource.getFavorites(userId)
    }

    override suspend fun addToFavorite(imageUrlDomainModel: ImageUrlDomainModel): Boolean {
        return favoritesDatasource.addToFavorite(imageUrlDomainModel.toDataModel())
    }

    override suspend fun deleteFromFavorites(imageUrlDomainModel: ImageUrlDomainModel): Boolean {
        return favoritesDatasource.deleteFromFavorites(imageUrlDomainModel.toDataModel())
    }

    override suspend fun getIsImageFavorite(imageUrlDomainModel: ImageUrlDomainModel): Boolean {
        return favoritesDatasource.isFavorite(imageUrlDomainModel.toDataModel())
    }

}