package com.example.eduardo.tecsa.domain.repository.trending

import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.ITrendingRemoteSource
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse

interface ITrendingRepository {
    suspend fun getTrendingMovieList(page: Int = 1): TrendingResponse
}

class TrendingRepository(
    private val remoteSource: ITrendingRemoteSource, private val localSource: ITrendingLocalSource
): ITrendingRepository {
    override suspend fun getTrendingMovieList(page: Int): TrendingResponse {
        return try {
            val movieList = remoteSource.fetchMovieList(page)
            localSource.saveMovieList(movieList.results)
            movieList
        } catch (_: Exception) {
            localSource.getAll()
        }
    }
}