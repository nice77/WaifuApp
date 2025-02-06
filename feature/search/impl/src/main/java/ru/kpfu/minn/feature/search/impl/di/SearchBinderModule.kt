package ru.kpfu.minn.feature.search.impl.di

import dagger.Binds
import dagger.Module
import ru.kpfu.minn.feature.search.api.repository.UserRepository
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository
import ru.kpfu.minn.feature.search.api.usecase.FetchTagsUseCase
import ru.kpfu.minn.feature.search.api.usecase.GetIsImageFavoriteUseCase
import ru.kpfu.minn.feature.search.api.usecase.ManageImageFavoritnessUseCase
import ru.kpfu.minn.feature.search.api.usecase.SetAsWallpaperUseCase
import ru.kpfu.minn.feature.search.impl.repository.UserRepositoryImpl
import ru.kpfu.minn.feature.search.impl.repository.WaifuImagesRepositoryImpl
import ru.kpfu.minn.feature.search.impl.usecase.FetchTagsUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.GetIsImageFavoriteUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.ManageImageFavoritnessUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.SetAsWallpaperUseCaseImpl

@Module
internal interface SearchBinderModule {

    @Binds
    fun bindWaifuImagesRepositoryToImpl(waifuImagesRepositoryImpl: WaifuImagesRepositoryImpl): WaifuImagesRepository

    @Binds
    fun bindFetchTagsUseCaseToImpl(fetchTagsUseCaseImpl: FetchTagsUseCaseImpl): FetchTagsUseCase

    @Binds
    fun bindUserRepositoryToImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindGetIsImageFavoriteToImpl(getIsImageFavoriteUseCaseImpl: GetIsImageFavoriteUseCaseImpl): GetIsImageFavoriteUseCase

    @Binds
    fun bindManageImageFavoritnessUseCaseToImpl(manageImageFavoritnessUseCaseImpl: ManageImageFavoritnessUseCaseImpl): ManageImageFavoritnessUseCase

    @Binds
    fun bindSetAsWallpaperUseCaseToImpl(setAsWallpaperUseCaseImpl: SetAsWallpaperUseCaseImpl): SetAsWallpaperUseCase

}