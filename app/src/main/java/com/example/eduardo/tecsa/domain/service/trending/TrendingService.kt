package com.example.eduardo.tecsa.domain.service.trending

import com.example.eduardo.tecsa.domain.repository.trending.TrendingRepository
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingService(private val trendingRepository: TrendingRepository) {
    suspend fun getTrendingMovieList(page: Int = 1): TrendingResponse {
        return withContext(Dispatchers.IO) {
            trendingRepository.getTrendingMovieList(page)
        }
    }
}