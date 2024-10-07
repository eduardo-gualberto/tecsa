package com.example.eduardo.tecsa.ui.screen.movieList

import com.example.eduardo.tecsa.domain.model.TMDBMovie

data class MovieListUIState(
    val movieList: List<TMDBMovie> = listOf(),
    val endReached: Boolean = false
)