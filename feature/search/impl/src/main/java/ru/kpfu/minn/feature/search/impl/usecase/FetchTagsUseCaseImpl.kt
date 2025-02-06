package ru.kpfu.minn.feature.search.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kpfu.minn.core.common.utils.ExceptionHandlerDelegate
import ru.kpfu.minn.core.common.utils.runSuspendCatching
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository
import ru.kpfu.minn.feature.search.api.usecase.FetchTagsUseCase
import javax.inject.Inject

class FetchTagsUseCaseImpl @Inject constructor(
    private val waifuImagesRepository: WaifuImagesRepository,
    private val dispatcher: CoroutineDispatcher,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate,
): FetchTagsUseCase {
    override suspend fun invoke(): Result<List<TagDomainModel>> = withContext(dispatcher) {
        runSuspendCatching(exceptionHandlerDelegate) {
            waifuImagesRepository.fetchTags()
        }
    }
}
