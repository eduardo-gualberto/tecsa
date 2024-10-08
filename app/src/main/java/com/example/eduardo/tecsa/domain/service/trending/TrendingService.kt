package com.example.eduardo.tecsa.domain.service.trending

import com.example.eduardo.tecsa.domain.repository.trending.ITrendingRepository
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ITrendingService {
    suspend fun getTrendingMovieList(page: Int = 1): TrendingResponse
}

class TrendingService(private val trendingRepository: ITrendingRepository) : ITrendingService {
    override suspend fun getTrendingMovieList(page: Int): TrendingResponse {
        return withContext(Dispatchers.IO) {
            trendingRepository.getTrendingMovieList(page)
        }
    }
}