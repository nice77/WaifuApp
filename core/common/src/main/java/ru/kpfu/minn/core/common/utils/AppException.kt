package ru.kpfu.minn.core.common.utils

import java.lang.Exception

sealed class AppException(message: String) : Exception(message) {
    class AuthInvalidCredentialsException(message: String) : AppException(message)
    class AuthUserDisabledException(message: String) : AppException(message)
    class AuthUnknownException(message: String) : AppException(message)

    class FirestorePermissionDeniedException(message: String) : AppException(message)
    class FirestoreServiceUnavailableException(message: String) : AppException(message)
    class FirestoreUnknownException(message: String) : AppException(message)

    class FirebaseGenericException(message: String) : AppException(message)
    class GeneralException(message: String) : AppException(message)

    class NetworkException(message: String) : AppException(message)
    class EmptyResponseException(message: String) : AppException(message)

    class HttpBadRequestException(message: String) : AppException(message)
    class HttpUnauthorizedException(message: String) : AppException(message)
    class HttpNotFoundException(message: String) : AppException(message)
    class HttpInternalServerErrorException(message: String) : AppException(message)
    class HttpUnknownException(message: String) : AppException(message)
}