package com.example.eduardo.tecsa.domain.repository.trending.source

import com.example.eduardo.tecsa.lib.api.TMDBApi
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse

interface ITrendingRemoteSource {
    suspend fun fetchMovieList(page: Int = 1): TrendingResponse
}

class TrendingRemoteSource(private val tmdbApi: TMDBApi): ITrendingRemoteSource {
    override suspend fun fetchMovieList(page: Int): TrendingResponse {
        return tmdbApi.getTrendingMovieList(page)
    }
}