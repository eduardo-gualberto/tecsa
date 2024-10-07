package com.example.eduardo.tecsa.domain.repository.trending.source

import android.util.Log
import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.lib.api.responses.TrendingResponse
import com.example.eduardo.tecsa.lib.database.MovieDatabase

class TrendingLocalSource() {
    fun getAll(): TrendingResponse {
        val database = MovieDatabase.getDatabase()
        val allMovies = database.movieDao().getAll()
        return TrendingResponse(total_pages = 1, results = allMovies)
    }

    suspend fun saveMovie(movie: TMDBMovie) {
        val database = MovieDatabase.getDatabase()
        return database.movieDao().insert(movie)
    }

    suspend fun saveMovieList(movieList: List<TMDBMovie>) {
        val database = MovieDatabase.getDatabase()
        return database.movieDao().insertMany(movieList)
    }
}