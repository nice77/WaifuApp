package ru.kpfu.minn.feature.search.api.model

data class UserDomainModel(
    val uid: String = "",
    val email: String = "",
    val username: String = "",
    val imageUrl: String? = null,
)
