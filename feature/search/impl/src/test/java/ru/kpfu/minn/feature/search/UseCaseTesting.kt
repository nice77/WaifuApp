package ru.kpfu.minn.feature.search

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.kpfu.minn.core.common.utils.AppException
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.model.UserDomainModel
import ru.kpfu.minn.feature.search.api.repository.UserRepository
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository
import ru.kpfu.minn.feature.search.impl.usecase.FetchTagsUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.GetIsCurrentUserUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.GetIsImageFavoriteUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.ManageImageFavoritnessUseCaseImpl
import ru.kpfu.minn.feature.search.impl.usecase.SetAsWallpaperUseCaseImpl

class UseCaseTesting {

    private val waifuImagesRepository = mockk<WaifuImagesRepository>()
    private val  exceptionHandlerDelegate = mockk<ExceptionHandlerDelegate>()
    private val userRepository = mockk<UserRepository>()

    @Before
    fun initMocks() {
        coEvery { waifuImagesRepository.fetchTags() } returns listOf(TagDomainModel(false, "maid"))
        coEvery { waifuImagesRepository.isFavorite(any()) } returns true
        coEvery { waifuImagesRepository.addToFavorite(any()) } returns Unit
        coEvery { exceptionHandlerDelegate.handleException(any()) } returns AppException.GeneralException("Test message")
        coEvery { userRepository.getIsCurrentUser(any()) } returns true
        coEvery { userRepository.setAsWallpaper(any()) } returns Unit
    }

    @Test
    fun assertTagsAreReturned() = runBlocking {
        val result = FetchTagsUseCaseImpl(
            waifuImagesRepository = waifuImagesRepository,
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
        ).invoke()
        assert(result.isSuccess && result.getOrThrow().isNotEmpty())
    }

    @Test
    fun getIsCurrentUseCaseShouldCallUserRepositoryMethod() = runBlocking {
        val getIsCurrentUserUseCaseImpl = GetIsCurrentUserUseCaseImpl(
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository,
        )
        getIsCurrentUserUseCaseImpl(userDomainModel = UserDomainModel())
        coVerify { userRepository.getIsCurrentUser(any()) }
    }

    @Test
    fun getIsCurrentUseCaseShouldReturnTrue() = runBlocking {
        val result = GetIsCurrentUserUseCaseImpl(
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            userRepository = userRepository,
        ).invoke(UserDomainModel()).getOrThrow()
        assert(result)
    }

    @Test
    fun getIsImageFavoriteUseCaseShouldCallRepositoryMethod() = runBlocking {
        GetIsImageFavoriteUseCaseImpl(
            waifuImagesRepository = waifuImagesRepository,
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
        ).invoke(imageDomainModel = ImageDomainModel("", "")).getOrThrow()
        coVerify { waifuImagesRepository.isFavorite(any()) }
    }

    @Test
    fun getIsImageFavoriteUseCaseShouldReturnTrue() = runBlocking {
        val result = GetIsImageFavoriteUseCaseImpl(
            waifuImagesRepository = waifuImagesRepository,
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
        ).invoke(imageDomainModel = ImageDomainModel("", "")).getOrThrow()
        assert(result)
    }

    @Test
    fun manageImageFavoritnessUseCaseShouldCallRepositoryMethod() = runBlocking {
        ManageImageFavoritnessUseCaseImpl(
            waifuImagesRepository = waifuImagesRepository,
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
        ).invoke(imageDomainModel = ImageDomainModel("", ""), false).getOrThrow()
        coVerify { waifuImagesRepository.addToFavorite(any()) }
    }

    @Test
    fun setAsWallpaperUseCaseShouldCallRepositoryMethod() = runBlocking {
        SetAsWallpaperUseCaseImpl(
            userRepository = userRepository,
            dispatcher = Dispatchers.IO,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
        ).invoke(imageDomainModel = ImageDomainModel("", "")).getOrThrow()
        coVerify { userRepository.setAsWallpaper(any()) }
    }

}