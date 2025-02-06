package ru.kpfu.minn.feature.profile.api.router

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRoute(
    val uid: String? = null,
)