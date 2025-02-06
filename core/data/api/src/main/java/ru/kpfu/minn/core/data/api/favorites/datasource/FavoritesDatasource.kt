package ru.kpfu.minn.core.data.api.favorites.datasource

import com.google.firebase.firestore.Query
import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl

interface FavoritesDatasource {

    suspend fun getFavorites(userId: String?): Query

    suspend fun addToFavorite(imageUrl: ImageUrl): Boolean

    suspend fun deleteFromFavorites(imageUrl: ImageUrl): Boolean

    suspend fun isFavorite(imageUrl: ImageUrl): Boolean

}