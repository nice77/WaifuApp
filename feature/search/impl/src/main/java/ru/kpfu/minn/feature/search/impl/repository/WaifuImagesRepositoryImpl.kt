package ru.kpfu.minn.feature.search.impl.repository

import ru.kpfu.minn.core.data.api.favorites.datasource.FavoritesDatasource
import ru.kpfu.minn.core.data.api.waifuimages.datasource.WaifuImagesDatasource
import ru.kpfu.minn.core.data.api.waifuimages.model.ImageModel
import ru.kpfu.minn.feature.search.api.model.ImageDomainModel
import ru.kpfu.minn.feature.search.api.model.TagDomainModel
import ru.kpfu.minn.feature.search.api.repository.WaifuImagesRepository
import ru.kpfu.minn.feature.search.impl.utils.toDataModel
import ru.kpfu.minn.feature.search.impl.utils.toDomainModel
import javax.inject.Inject

internal class WaifuImagesRepositoryImpl @Inject constructor(
    private val waifuImagesDatasource: WaifuImagesDatasource,
    private val favoritesDatasource: FavoritesDatasource,
): WaifuImagesRepository {

    override suspend fun fetchTags(): List<TagDomainModel> =
        waifuImagesDatasource.fetchTags().toDomainModel()

    override suspend fun fetchImages(tags: List<TagDomainModel>): List<ImageDomainModel> =
        waifuImagesDatasource
            .fetchImagesByTags(tags.map(TagDomainModel::toDataModel))
            .map(ImageModel::toDomainModel)

    override suspend fun removeFromFavorite(imageDomainModel: ImageDomainModel) {
        favoritesDatasource.deleteFromFavorites(imageDomainModel.toDataModel())
    }

    override suspend fun addToFavorite(imageDomainModel: ImageDomainModel) {
        favoritesDatasource.addToFavorite(imageDomainModel.toDataModel())
    }

    override suspend fun isFavorite(imageDomainModel: ImageDomainModel): Boolean {
        return favoritesDatasource.isFavorite(imageDomainModel.toDataModel())
    }

}