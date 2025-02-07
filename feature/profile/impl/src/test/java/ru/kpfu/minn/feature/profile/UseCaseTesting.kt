package ru.kpfu.minn.feature.profile

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.feature.profile.api.model.ImageUrlDomainModel
import ru.kpfu.minn.feature.profile.api.model.UserDomainModel
import ru.kpfu.minn.feature.profile.api.repository.FavoritesRepository
import ru.kpfu.minn.feature.profile.api.repository.UserRepository
import ru.kpfu.minn.feature.profile.impl.usecase.AddToFavoritesUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.GetIsImageFavoriteUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.GetUserInfoUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.RemoveFromFavoritesUseCaseImpl
import ru.kpfu.minn.feature.profile.impl.usecase.SetAsWallpaperUseCaseImpl

class UseCaseTesting {

    private val dispatcher = Dispatchers.IO
    private val exceptionHandlerDelegate = mockk<ExceptionHandlerDelegate>()
    private val favoritesRepository = mockk<FavoritesRepository>()
    private val userRepository = mockk<UserRepository>()

    @Before
    fun initMocks() {
        coEvery { favoritesRepository.addToFavorite(any()) } returns true
        coEvery { favoritesRepository.deleteFromFavorites(any()) } returns true
        coEvery { favoritesRepository.getIsImageFavorite(any()) } returns true
        coEvery { userRepository.fetchUserInfo(any()) } returns UserDomainModel("", "")
        coEvery { userRepository.updateUserAvatar(any()) } returns true
    }

    @Test
    fun addToFavoritesUseCaseShouldCallRepositoryMethods() = runBlocking {
        AddToFavoritesUseCaseImpl(
            favoritesRepository = favoritesRepository,
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        coVerify { favoritesRepository.addToFavorite(any()) }
    }

    @Test
    fun addToFavoritesUseCaseShouldReturnTrue() = runBlocking {
        val result = AddToFavoritesUseCaseImpl(
            favoritesRepository = favoritesRepository,
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        assert(result)
    }

    @Test
    fun removeFromFavoritesUseCaseShouldCallRepositoryMethods() = runBlocking {
        RemoveFromFavoritesUseCaseImpl(
            favoritesRepository = favoritesRepository,
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        coVerify { favoritesRepository.deleteFromFavorites(any()) }
    }

    @Test
    fun removeFromFavoritesUseCaseShouldReturnTrue() = runBlocking {
        val result = RemoveFromFavoritesUseCaseImpl(
            favoritesRepository = favoritesRepository,
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        assert(result)
    }

    @Test
    fun getIsImageFavoriteUseCaseShouldCallRepositoryMethod() = runBlocking {
        GetIsImageFavoriteUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            favoritesRepository = favoritesRepository,
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        coVerify { favoritesRepository.getIsImageFavorite(any()) }
    }

    @Test
    fun getIsImageFavoriteUseCaseShouldReturnTrue() = runBlocking {
        val result = GetIsImageFavoriteUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            favoritesRepository = favoritesRepository,
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        assert(result)
    }

    @Test
    fun getUserInfoUseCaseShouldCallRepositoryMethod() = runBlocking {
        GetUserInfoUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository
        ).invoke(null).getOrThrow()
        coVerify { userRepository.fetchUserInfo(any()) }
    }

    @Test
    fun getUserInfoUseCaseShouldReturnTrue() = runBlocking {
        val result = GetUserInfoUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository
        ).invoke(null).getOrThrow()
        assert(result == UserDomainModel("", ""))
    }

    @Test
    fun setAsWallpaperUseCaseShouldCallRepositoryMethod() = runBlocking {
        SetAsWallpaperUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        coVerify { userRepository.updateUserAvatar(any()) }
    }

    @Test
    fun setAsWallpaperUseCaseShouldReturnTrue() = runBlocking {
        val result = SetAsWallpaperUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository
        ).invoke(ImageUrlDomainModel()).getOrThrow()
        assert(result)
    }

}