package com.example.eduardo.tecsa.ui.screen.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.domain.service.trending.TrendingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface IMovieListViewModel {
    val uiState: StateFlow<MovieListUIState>
    fun fetchMovieList()
}

class MovieListViewModel(
    private val trendingService: TrendingService
) : ViewModel(), IMovieListViewModel {
    private val _uiState = MutableStateFlow(MovieListUIState())
    private var nextPage = 1

    override val uiState = _uiState.asStateFlow()

    init {
        fetchMovieList()
    }

    override fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.Main) {
            val trendingMoviesResponse = trendingService.getTrendingMovieList(nextPage)

            nextPage += 1
            _uiState.update { currState ->
                currState.copy(
                    movieList = removeDuplicates(currState.movieList + trendingMoviesResponse.results),
                    endReached = nextPage > trendingMoviesResponse.total_pages
                )
            }
        }
    }

    private fun removeDuplicates(movieList: List<TMDBMovie>): List<TMDBMovie> {
        return movieList.toSet().toList()
    }
}
