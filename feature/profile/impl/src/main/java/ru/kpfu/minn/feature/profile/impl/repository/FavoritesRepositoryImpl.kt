package ru.kpfu.minn.feature.profile.impl.repository

import com.google.firebase.firestore.Query
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import javax.inject.Inject

internal class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDatasource: FavoritesDatasource,
): FavoritesRepository {
    override suspend fun getFavorites(userId: String?): Query {
        return favoritesDatasource.getFavorites(userId)
    }

    override suspend fun addToFavorite(imageUrlDomainModel: ImageUrlDomainModel): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorites(imageUrlDomainModel: ImageUrlDomainModel): Boolean {
        TODO("Not yet implemented")
    }

}