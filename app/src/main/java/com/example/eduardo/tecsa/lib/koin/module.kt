package com.example.eduardo.tecsa.lib.koin

import com.example.eduardo.tecsa.domain.repository.trending.TrendingRepository
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingRemoteSource
import com.example.eduardo.tecsa.domain.service.trending.TrendingService
import com.example.eduardo.tecsa.ui.screen.movieList.MovieListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::TrendingLocalSource)
    singleOf(::TrendingRemoteSource)
    singleOf(::TrendingRepository)
    singleOf(::TrendingService)

    viewModelOf(::MovieListViewModel)
}