package ru.kpfu.minn.core.data.impl.firebase.favorites

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.common.utils.AppException
import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl
import javax.inject.Inject

class FavoritesDatasourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
): FavoritesDatasource {

    override suspend fun getFavorites(
        userId: String?
    ): Query = firebaseFirestore
            .collection("users")
            .document(firebaseAuth.currentUser?.uid ?: throw AppException.AuthUnknownException("Unathorized user"))
            .collection("favorites")
            .limit(PAGE_SIZE)

    override suspend fun addToFavorite(imageUrl: ImageUrl): Boolean {
        firebaseFirestore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("favorites")
            .document(imageUrl.imageUrl.split("/").last())
            .set(imageUrl)
            .await()
        return true
    }

    override suspend fun deleteFromFavorites(imageUrl: ImageUrl): Boolean {
        firebaseFirestore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("favorites")
            .document(imageUrl.imageUrl.split("/").last())
            .delete()
            .await()
        return true
    }

    override suspend fun isFavorite(imageUrl: ImageUrl): Boolean {
        return firebaseFirestore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("favorites")
            .document(imageUrl.imageUrl.split("/").last())
            .get()
            .await()
            .toObject(ImageUrl::class.java) != null
    }

    companion object {
            const val PAGE_SIZE = 20L
    }

}