package ru.kpfu.minn.feature.register

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.data.api.users.UserService
import ru.kpfu.minn.core.data.api.users.model.User
import ru.kpfu.minn.feature.register.api.model.UserDomainModel
import ru.kpfu.minn.feature.register.api.repository.CloudMessagingRepository
import ru.kpfu.minn.feature.register.api.repository.UserRepository
import ru.kpfu.minn.feature.register.impl.usecase.RegisterUserUseCaseImpl
import ru.kpfu.minn.feature.register.impl.usecase.RetrieveFCMTokenUseCaseImpl

class UseCaseTesting {

    private val exceptionHandlerDelegate = mockk<ExceptionHandlerDelegate>()
    private val dispatcher = Dispatchers.IO
    private val userService = mockk<UserService>()
    private val userRepository = mockk<UserRepository>()
    private val cloudMessagingRepository = mockk<CloudMessagingRepository>()

    @Before
    fun initMocks() {
        coEvery { userService.registerUser(any(), any()) } returns User("", "")
        coEvery { userRepository.addUser(any()) } returns UserDomainModel("", "", "", "", "")
        coEvery { cloudMessagingRepository.retrieveToken() } returns  ""
    }

    @Test
    fun retrieveTokenUseCaseShouldCallRepositoryMethod() = runBlocking {
        RetrieveFCMTokenUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            cloudMessagingRepository = cloudMessagingRepository
        ).invoke().getOrThrow()
        coVerify { cloudMessagingRepository.retrieveToken() }
    }

    @Test
    fun retrieveTokenUseCaseShouldReturnEmptyToken() = runBlocking {
        val token = RetrieveFCMTokenUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            cloudMessagingRepository = cloudMessagingRepository
        ).invoke().getOrThrow()
        assert(token.isEmpty())
    }

    @Test
    fun registerUseCaseShouldCallBothMethods() = runBlocking {
        RegisterUserUseCaseImpl(
            userService = userService,
            userRepository = userRepository,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            dispatcher = dispatcher,
        ).invoke("", "", "", "").getOrThrow()
        coVerify {
            userService.registerUser(any(), any())
            userRepository.addUser(any())
        }
    }

}