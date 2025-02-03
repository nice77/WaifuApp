package ru.kpfu.minn.core.data.api.users.model

data class UserDetails (
    val uid: String = "",
    val email: String = "",
    val username: String = "",
    val imageUrl: String? = null,
)