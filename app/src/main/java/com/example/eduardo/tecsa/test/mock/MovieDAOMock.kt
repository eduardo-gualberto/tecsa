package com.example.eduardo.tecsa.test.mock

import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.lib.database.MovieDAO

class MovieDAOMock: MovieDAO {
    private val movies = mutableListOf<TMDBMovie>()

    override suspend fun insertMany(movieList: List<TMDBMovie>) {
        println("SALVO MESMO")
        this.movies.addAll(movieList)
    }

    override fun getAll(): List<TMDBMovie> {
        return this.movies
    }
}