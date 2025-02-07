package ru.kpfu.minn.feature.messaging.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.feature.messaging.api.model.FCMMessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.model.UserDetailsDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.ChatsRepository
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository
import ru.kpfu.minn.feature.messaging.api.repository.UsersRepository
import ru.kpfu.minn.feature.messaging.impl.usecase.AddChatUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetChatIdByOtherUserUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetChatsUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetCurrentUserUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetMessagesUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.GetUserDetailsUseCaseImpl
import ru.kpfu.minn.feature.messaging.impl.usecase.SendMessageUseCaseImpl


class UseCaseTesting {

    private val dispatcher = Dispatchers.IO
    private val exceptionHandlerDelegate = mockk<ExceptionHandlerDelegate>()
    private val usersRepository = mockk<UsersRepository>()
    private val chatsRepository = mockk<ChatsRepository>()
    private val messagesRepository = mockk<MessagesRepository>()


    @Before
    fun initMocks() {
        coEvery { chatsRepository.addChat(any(), any(), any()) } returns Unit
        coEvery { chatsRepository.getChatByOtherUserId(any()) } returns null
        coEvery { chatsRepository.getChats() } returns listOf()
        coEvery { usersRepository.getCurrentUserId() } returns ""
        coEvery { usersRepository.getUserDetails(any()) } returns UserDetailsDomainModel("", "", "", "")
        coEvery { messagesRepository.getMessages(any()) } returns listOf()
        coEvery { messagesRepository.sendMessage(any(), any()) } returns Unit
        coEvery { messagesRepository.sendNotification(any()) } returns Unit
    }

    @Test
    fun addChatUseCaseShouldCallRepositoryMethods() = runBlocking {
        AddChatUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository,
            usersRepository = usersRepository
        ).invoke("").getOrThrow()
        coVerify {
            usersRepository.getCurrentUserId()
            chatsRepository.addChat(any(), any(), any())
        }
    }

    @Test
    fun addChatUseCaseShouldReturnNonEmptyId() = runBlocking {
        val result = AddChatUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository,
            usersRepository = usersRepository
        ).invoke("").getOrThrow()
        assert(result.isNotEmpty())
    }

    @Test
    fun getChatIdByOtherUserUseCaseShouldCallRepositoryMethods() = runBlocking {
        val otherUserId = ""
        GetChatIdByOtherUserUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository
        ).invoke(otherUserId).getOrThrow()
        coVerify { chatsRepository.getChatByOtherUserId(otherUserId) }
    }


    @Test
    fun getChatIdByOtherUserUseCaseShouldReturnNull() = runBlocking {
        val otherUserId = ""
        val result = GetChatIdByOtherUserUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository
        ).invoke(otherUserId).getOrThrow()
        assert(result == null)
    }

    @Test
    fun getChatsUseCaseShouldCallRepositoryMethods() = runBlocking {
        GetChatsUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository
        ).invoke().getOrThrow()
        coVerify { chatsRepository.getChats() }
    }

    @Test
    fun getChatsUseCaseShouldReturnEmptyList() = runBlocking {
        val result = GetChatsUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            chatsRepository = chatsRepository
        ).invoke().getOrThrow()
        assert(result.isEmpty())
    }

    @Test
    fun getCurrentUserUseCaseShouldCallRepositoryMethods() = runBlocking {
        GetCurrentUserUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            usersRepository = usersRepository,
        ).invoke().getOrThrow()
        coVerify { usersRepository.getCurrentUserId() }
    }

    @Test
    fun getCurrentUserUseCaseShouldReturnEmptyString() = runBlocking {
        val result = GetCurrentUserUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            usersRepository = usersRepository,
        ).invoke().getOrThrow()
        assert(result.isEmpty())
    }

    @Test
    fun getMessagesUseCaseShouldCallRepositoryMethods() = runBlocking {
        val chatId = ""
        GetMessagesUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            messagesRepository = messagesRepository
        ).invoke(chatId).getOrThrow()
        coVerify { messagesRepository.getMessages(chatId) }
    }

    @Test
    fun getMessagesUseCaseShouldReturnEmptyList() = runBlocking {
        val chatId = ""
        val result = GetMessagesUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            messagesRepository = messagesRepository
        ).invoke(chatId).getOrThrow()
        assert(result.isEmpty())
    }

    @Test
    fun getUserDetailsUseCaseShouldCallRepositoryMethods() = runBlocking {
        val userId = ""
        GetUserDetailsUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            usersRepository = usersRepository
        ).invoke(userId).getOrThrow()
        coVerify { usersRepository.getUserDetails(userId) }
    }

    @Test
    fun getUserDetailsUseCaseShouldReturnUser() = runBlocking {
        val userId = ""
        val actual = GetUserDetailsUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            usersRepository = usersRepository
        ).invoke(userId).getOrThrow()
        val expected = UserDetailsDomainModel("", "", "", "")
        assert(actual == expected)
    }

    @Test
    fun sendMessageUseCaseShouldCallRepositoryMethods() = runBlocking {
        val chatId = ""
        val otherUserId = ""
        val messageDomainModel = MessageDomainModel(0L, "", "")
        SendMessageUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            messagesRepository = messagesRepository,
            usersRepository = usersRepository
        ).invoke(chatId, otherUserId, messageDomainModel).getOrThrow()
        coVerify {
            messagesRepository.sendMessage(chatId, messageDomainModel)
            usersRepository.getUserDetails(otherUserId)
            messagesRepository.sendNotification(FCMMessageDomainModel("", "", ""))
        }
    }

    @Test
    fun sendMessageUseCaseShouldReturnMessageDomainModel() = runBlocking {
        val chatId = ""
        val otherUserId = ""
        val messageDomainModel = MessageDomainModel(0L, "", "")
        val actual = SendMessageUseCaseImpl(
            dispatcher = dispatcher,
            exceptionHandlerDelegate = exceptionHandlerDelegate,
            messagesRepository = messagesRepository,
            usersRepository = usersRepository
        ).invoke(chatId, otherUserId, messageDomainModel).getOrThrow()
        assert(actual == messageDomainModel)
    }
}