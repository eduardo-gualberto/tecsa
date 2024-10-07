package com.example.eduardo.tecsa.ui.screen.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.ui.component.MovieCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier, viewModel: MovieListViewModel = koinViewModel()
) {
    val mainUiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index != 0 && lastVisibleItem.index >= listState.layoutInfo.totalItemsCount - 5
        }
    }

    LaunchedEffect(reachedBottom, mainUiState.endReached) {
        if (reachedBottom && !mainUiState.endReached) viewModel.fetchMovieList()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        items(items = mainUiState.movieList, key = { movie: TMDBMovie -> movie.id }) {
            MovieCard(movie = it)
            Spacer(Modifier.height(8.dp))
        }
    }
}