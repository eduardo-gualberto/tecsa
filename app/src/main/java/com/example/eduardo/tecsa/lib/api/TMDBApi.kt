package com.example.eduardo.tecsa.lib.api

import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("trending/movie/day")
    suspend fun getTrendingMovieList(@Query("page") page: Int = 1): TrendingResponse
}