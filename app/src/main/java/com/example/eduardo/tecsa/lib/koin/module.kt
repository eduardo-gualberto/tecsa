package com.example.eduardo.tecsa.lib.koin

import com.example.eduardo.tecsa.domain.repository.trending.ITrendingRepository
import com.example.eduardo.tecsa.domain.repository.trending.TrendingRepository
import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingRemoteSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingRemoteSource
import com.example.eduardo.tecsa.domain.service.trending.ITrendingService
import com.example.eduardo.tecsa.domain.service.trending.TrendingService
import com.example.eduardo.tecsa.lib.api.RetrofitInstance
import com.example.eduardo.tecsa.lib.api.TMDBApi
import com.example.eduardo.tecsa.lib.database.IMovieDatabase
import com.example.eduardo.tecsa.lib.database.MovieDAO
import com.example.eduardo.tecsa.lib.database.MovieDatabase
import com.example.eduardo.tecsa.ui.screen.movieList.IMovieListViewModel
import com.example.eduardo.tecsa.ui.screen.movieList.MovieListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    singleOf(::TrendingLocalSource) bind ITrendingLocalSource::class
    singleOf(::TrendingRemoteSource) bind ITrendingRemoteSource::class
    singleOf(::TrendingRepository) bind ITrendingRepository::class
    singleOf(::TrendingService) bind ITrendingService::class
    single<IMovieDatabase> {
        MovieDatabase.getDatabase()
    }
    single<MovieDAO> {
        get<IMovieDatabase>()
            .movieDao()
    }
    single<TMDBApi> {
        RetrofitInstance.retrofit().create(TMDBApi::class.java)
    }

    viewModelOf(::MovieListViewModel) bind IMovieListViewModel::class
}