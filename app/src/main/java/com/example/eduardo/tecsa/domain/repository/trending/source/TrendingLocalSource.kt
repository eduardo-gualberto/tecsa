package com.example.eduardo.tecsa.domain.repository.trending.source

import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import com.example.eduardo.tecsa.lib.database.IMovieDatabase

interface ITrendingLocalSource {
    fun getAll(): TrendingResponse
    suspend fun saveMovieList(movieList: List<TMDBMovie>)
}

class TrendingLocalSource(private val database: IMovieDatabase): ITrendingLocalSource {
    override fun getAll(): TrendingResponse {
        val allMovies = database.movieDao().getAll()
        return TrendingResponse(total_pages = 1, results = allMovies)
    }

    override suspend fun saveMovieList(movieList: List<TMDBMovie>) {
        println("SALVANDO")
        return database.movieDao().insertMany(movieList)
    }
}