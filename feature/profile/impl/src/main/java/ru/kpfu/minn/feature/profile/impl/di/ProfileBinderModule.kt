package ru.kpfu.minn.feature.profile.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.api.repository.UserRepository
import ru.kpfu.minn.feature.profile.api.usecase.AddToFavoritesUseCase
import ru.kpfu.minn.feature.profile.api.usecase.GetIsImageFavoriteUseCase
import ru.kpfu.minn.feature.profile.api.usecase.GetUserInfoUseCase
import ru.kpfu.minn.feature.profile.api.usecase.RemoveFromFavoritesUseCase
import ru.kpfu.minn.feature.profile.api.usecase.SetAsWallpaperUseCase
import ru.kpfu.minn.feature.profile.impl.repository.FavoritesRepositoryImpl
import ru.kpfu.minn.feature.profile.impl.repository.UserRepositoryImpl
import ru.kpfu.minn.feature.profile.impl.usecase.AddToFavoritesUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.GetIsImageFavoriteUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.GetUserInfoUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.RemoveFromFavoritesUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.SetAsWallpaperUseCaseImpl

@Module
internal interface ProfileBinderModule {

    @Binds
    fun bindGetUserInfoUseCaseToImpl(getUserInfoUseCaseImpl: GetUserInfoUseCaseImpl): GetUserInfoUseCase

    @Binds
    fun bindFavoritesRepositoryToImpl(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindUserRepositoryToImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRemoveFromFavoritesUseCaseToImpl(removeFromFavoritesUseCaseImpl: RemoveFromFavoritesUseCaseImpl): RemoveFromFavoritesUseCase

    @Binds
    fun bindAddToFavoritesUseCaseToImpl(addToFavoritesUseCaseImpl: AddToFavoritesUseCaseImpl): AddToFavoritesUseCase

    @Binds
    fun bindSetAsWallpaperUseCaseImpl(setAsWallpaperUseCaseImpl: SetAsWallpaperUseCaseImpl): SetAsWallpaperUseCase

    @Binds
    fun bindGetIsImageFavoriteUseCaseToImpl(getIsImageFavoriteUseCaseImpl: GetIsImageFavoriteUseCaseImpl): GetIsImageFavoriteUseCase

}