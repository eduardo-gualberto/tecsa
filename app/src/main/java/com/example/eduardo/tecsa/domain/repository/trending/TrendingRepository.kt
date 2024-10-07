package com.example.eduardo.tecsa.domain.repository.trending

import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingLocalSource
import com.example.eduardo.tecsa.domain.repository.trending.source.TrendingRemoteSource
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse

class TrendingRepository(
    private val remoteSource: TrendingRemoteSource, private val localSource: TrendingLocalSource
) {
    suspend fun getTrendingMovieList(page: Int = 1): TrendingResponse {
        return try {
            val movieList = remoteSource.fetchMovieList(page)
            localSource.saveMovieList(movieList.results)
            movieList
        } catch (_: Exception) {
            localSource.getAll()
        }
    }
}