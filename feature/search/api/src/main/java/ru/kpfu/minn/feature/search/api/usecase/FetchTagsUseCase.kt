package ru.kpfu.minn.feature.search.api.usecase

import ru.kpfu.minn.feature.search.api.model.TagDomainModel

interface FetchTagsUseCase {

    suspend operator fun invoke(): Result<List<TagDomainModel>>

}