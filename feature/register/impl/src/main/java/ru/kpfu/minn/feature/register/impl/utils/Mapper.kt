package ru.kpfu.minn.feature.register.impl.utils

import ru.kpfu.minn.core.data.api.users.model.UserDetails
import ru.kpfu.minn.feature.register.api.model.UserDomainModel

fun UserDomainModel.toUserDetails(): UserDetails {
    return UserDetails(
        this.uid,
        this.email,
        this.username,
        this.imageUrl,
    )
}

fun UserDetails.toUserDomainModel(): UserDomainModel {
    return UserDomainModel(
        this.uid,
        this.email,
        this.username,
        this.imageUrl,
    )
}
