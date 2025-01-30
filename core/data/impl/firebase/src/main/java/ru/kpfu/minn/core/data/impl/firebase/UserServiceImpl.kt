package ru.kpfu.minn.core.data.impl.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.kpfu.minn.core.data.api.users.UserService
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): UserService {

    override suspend fun authorizeUser(email: String, password: String): Boolean {
        val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return user.user != null
    }

}