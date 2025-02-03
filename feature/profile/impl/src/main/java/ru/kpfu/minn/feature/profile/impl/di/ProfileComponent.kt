package ru.kpfu.minn.feature.profile.impl.di

import dagger.Component
import dagger.Component.Factory
import ru.kpfu.minn.feature.profile.impl.repository.paging.FavoritesPagingSource
import ru.kpfu.minn.feature.profile.impl.ui.ProfileViewModel


@Component(
    modules = [ProfileModule::class],
    dependencies = [ProfileDependencies::class],
)
internal interface ProfileComponent: ProfileDependencies {

    fun favoritesPagingSource(): FavoritesPagingSource.FavoritesPagingSourceFactory

    fun inject(viewModel: ProfileViewModel)

    @Factory
    interface ProfileFactory {
        fun create(profileDependencies: ProfileDependencies): ProfileComponent
    }

}