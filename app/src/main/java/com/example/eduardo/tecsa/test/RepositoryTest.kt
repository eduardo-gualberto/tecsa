package com.example.eduardo.tecsa.test

import com.example.eduardo.tecsa.domain.repository.trending.ITrendingRepository
import com.example.eduardo.tecsa.domain.repository.trending.TrendingRepository
import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingRemoteSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingRemoteSource
import com.example.eduardo.tecsa.lib.api.TMDBApi
import com.example.eduardo.tecsa.lib.database.IMovieDatabase
import com.example.eduardo.tecsa.lib.database.MovieDAO
import com.example.eduardo.tecsa.lib.database.MovieDatabase
import com.example.eduardo.tecsa.test.mock.MovieDatabaseMock
import com.example.eduardo.tecsa.test.mock.TMDBApiMock
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.verify.verify
import kotlin.test.assertTrue

val testModule = module {
    singleOf(::TrendingLocalSource) bind ITrendingLocalSource::class
    singleOf(::TrendingRemoteSource) bind ITrendingRemoteSource::class
    singleOf(::TrendingRepository) bind ITrendingRepository::class
    single<IMovieDatabase> {
        MovieDatabaseMock
    }
    single<MovieDAO> {
        get<IMovieDatabase>().movieDao()
    }
    single<TMDBApi> {
        TMDBApiMock()
    }
}

class RepositoryTest: KoinTest {
    val trendingRepository: ITrendingRepository by inject()

    @Before
    fun setuo() {
        startKoin { modules(testModule) }
    }

    @After
    fun cleanup() {
        stopKoin()
    }

    @Test
    fun verify_koin_module() {
        testModule.verify()
    }

    @Test
    fun should_return_api_data() {
        TMDBApiMock.FORCE_ERROR = false
        val movies = runBlocking {
            trendingRepository.getTrendingMovieList(1)
        }
        assertTrue(movies.results.size == 3)
    }

    @Test
    fun should_return_db_data_empty() {
        TMDBApiMock.FORCE_ERROR = true
        val movies = runBlocking {
            trendingRepository.getTrendingMovieList(1)
        }
        assertTrue(movies.results.size == 0)
    }
}