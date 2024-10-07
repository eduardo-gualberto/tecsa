package com.example.eduardo.tecsa.lib.api

import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovieList(@Query("page") page: Int = 1): TrendingResponse
}