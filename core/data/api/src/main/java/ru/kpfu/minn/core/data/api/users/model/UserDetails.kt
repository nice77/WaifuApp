package ru.kpfu.minn.core.data.api.users.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetails (
    @SerialName("uid")
    val uid: String,
    @SerialName("email")
    val email: String,
    @SerialName("username")
    val username: String,
    @SerialName("imageUrl")
    val imageUrl: String,
)