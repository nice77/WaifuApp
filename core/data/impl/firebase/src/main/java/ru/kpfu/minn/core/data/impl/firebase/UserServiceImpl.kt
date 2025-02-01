package ru.kpfu.minn.core.data.impl.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.model.User
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): UserService {

    override suspend fun authorizeUser(email: String, password: String): Boolean {
        val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return user.user != null
    }

    override suspend fun registerUser(email: String, password: String): User? {
        val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
        return authResult?.let {
            User(it.uid, it.email!!)
        }
    }

    override fun getCurrentUserID(): String? = firebaseAuth.currentUser?.uid


}