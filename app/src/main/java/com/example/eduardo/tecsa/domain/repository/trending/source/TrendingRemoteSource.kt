package com.example.eduardo.tecsa.domain.repository.trending.source

import com.example.eduardo.tecsa.lib.api.APIs
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse

class TrendingRemoteSource {
    suspend fun fetchMovieList(page: Int = 1): TrendingResponse {
        return APIs.tmdbApi.getTrendingMovieList(page)
    }
}