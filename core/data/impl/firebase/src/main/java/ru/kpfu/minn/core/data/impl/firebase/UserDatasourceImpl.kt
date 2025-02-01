package ru.kpfu.minn.core.data.impl.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.users.datasource.UserDatasource
import ru.kpfu.minn.core.data.api.users.model.UserDetails
import javax.inject.Inject

class UserDatasourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): UserDatasource {

    override suspend fun addUser(userDetails: UserDetails): UserDetails {
        firebaseFirestore.collection("users")
            .document(userDetails.uid)
            .set(userDetails)
            .await()
        return userDetails
    }

}