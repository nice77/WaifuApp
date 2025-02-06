package ru.kpfu.minn.feature.search.impl.di

import dagger.Component
import dagger.Component.Factory
import ru.kpfu.minn.core.common.di.FeatureScope
import ru.kpfu.minn.feature.search.impl.repository.paging.SearchPagingSource
import ru.kpfu.minn.feature.search.impl.repository.paging.UserPagingSource
import ru.kpfu.minn.feature.search.impl.ui.SearchViewModel

@Component(
    modules = [SearchModule::class],
    dependencies = [SearchDependencies::class],
)
@FeatureScope
internal interface SearchComponent: SearchDependencies {

    @Factory
    interface SearchComponentFactory {
        fun create(dependencies: SearchDependencies): SearchComponent
    }

    fun userPagingSourceFactory(): UserPagingSource.Factory

    fun searchPagingSourceFactory(): SearchPagingSource.Factory

    fun inject(viewModel: SearchViewModel)

}