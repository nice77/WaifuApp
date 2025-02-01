package ru.kpfu.minn.core.common.utils

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestoreException
import javax.inject.Inject

class ExceptionHandlerDelegate @Inject constructor() {

    fun handleException(exception: Throwable): AppException =
        when (exception) {
            is FirebaseAuthException -> handleAuthException(exception)
            is FirebaseFirestoreException -> handleFirestoreException(exception)
            is FirebaseException -> handleGeneralFirebaseException(exception)
            else -> handleGeneralException(exception)
        }

    private fun handleAuthException(exception: FirebaseAuthException): AppException =
        when (exception) {
            is FirebaseAuthInvalidCredentialsException ->
                AppException.AuthInvalidCredentialsException(exception.message.toString())
            is FirebaseAuthInvalidUserException ->
                AppException.AuthUserDisabledException(exception.message.toString())
            is FirebaseAuthUserCollisionException ->
                AppException.EmailAlreadyInUseException(exception.message.toString())
            else -> AppException.AuthUnknownException("An unknown authentication error occurred.")
        }

    private fun handleFirestoreException(exception: FirebaseFirestoreException): AppException =
        when (exception.code) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                AppException.FirestorePermissionDeniedException(
                    "You do not have permission to access this resource."
                )
            FirebaseFirestoreException.Code.UNAVAILABLE ->
                AppException.FirestoreServiceUnavailableException(
                    "Firestore service is currently unavailable."
                )
            else -> AppException.FirestoreUnknownException(
                "An unknown Firestore error occurred."
            )
        }

    private fun handleGeneralFirebaseException(exception: FirebaseException): AppException =
        AppException.FirebaseGenericException(
            "${exception.message}"
        )

    private fun handleGeneralException(exception: Throwable): AppException =
        AppException.GeneralException("${exception.message}")
}