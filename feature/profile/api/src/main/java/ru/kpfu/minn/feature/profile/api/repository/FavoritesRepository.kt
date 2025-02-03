package ru.kpfu.minn.feature.profile.api.repository

import com.google.firebase.firestore.Query
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel

interface FavoritesRepository {

    suspend fun getFavorites(userId: String?): Query

    suspend fun addToFavorite(imageUrlDomainModel: ImageUrlDomainModel): Boolean

    suspend fun deleteFromFavorites(imageUrlDomainModel: ImageUrlDomainModel): Boolean

}