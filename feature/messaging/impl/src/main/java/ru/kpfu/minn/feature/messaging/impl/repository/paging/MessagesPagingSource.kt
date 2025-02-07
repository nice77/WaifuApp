package ru.kpfu.minn.feature.messaging.impl.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.kpfu.minn.feature.messaging.api.model.MessageDomainModel
import ru.kpfu.minn.feature.messaging.api.repository.MessagesRepository

internal class MessagesPagingSource @AssistedInject constructor(
    private val messagesRepository: MessagesRepository,
    @Assisted private val chatId: String,
): PagingSource<Long, MessageDomainModel>() {

    private var lastTimestamp = 0L

    override fun getRefreshKey(state: PagingState<Long, MessageDomainModel>): Long? {
        return null
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MessageDomainModel> {
        return try {
            val currentKey = params.key ?: 0
            val pageSize = params.loadSize
            val page = messagesRepository.getMessages(chatId, pageSize, lastTimestamp)
            this.lastTimestamp = page.last().timestamp
            val nextKey = if (page.size < pageSize) null else currentKey + 1
            LoadResult.Page(
                data = page,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface MessagesPagingSourceFactory {
        fun create(@Assisted chatId: String): MessagesPagingSource
    }
}