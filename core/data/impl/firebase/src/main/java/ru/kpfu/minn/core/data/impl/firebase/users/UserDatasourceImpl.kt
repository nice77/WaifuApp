package ru.kpfu.minn.core.data.impl.firebase.users

import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.favorites.model.ImageUrl
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import javax.inject.Inject

internal class UserDatasourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val userService: UserService,
): UserDatasource {

    override suspend fun addUser(userDetails: UserDetails): UserDetails {
        firebaseFirestore.collection("users")
            .document(userDetails.uid)
            .set(userDetails)
            .await()
        return userDetails
    }

    override suspend fun getUser(userId: String): UserDetails {
        return firebaseFirestore.collection("users")
            .document(userId)
            .get()
            .await()
            .toObject(UserDetails::class.java)!!
    }

    override suspend fun updateUserAvatar(imageUrl: ImageUrl): Boolean {
        firebaseFirestore.collection("users")
            .document(userService.getCurrentUserID()!!)
            .update("imageUrl", imageUrl.imageUrl)
            .await()
        return true
    }

    override suspend fun fetchUsers(page: Long, pageSize: Int, searchQuery: String): List<UserDetails> {
        return firebaseFirestore.collection("users")
            .orderBy("username")
            .where(Filter.greaterThanOrEqualTo("username", searchQuery))
            .where(Filter.lessThanOrEqualTo("username", searchQuery + "\uf8ff"))
            .startAt(page * pageSize)
            .limit(pageSize.toLong())
            .get()
            .await()
            .toObjects(UserDetails::class.java)
    }
}
