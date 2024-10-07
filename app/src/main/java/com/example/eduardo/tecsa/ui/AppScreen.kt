package com.example.eduardo.tecsa.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eduardo.tecsa.ui.component.TopAppBar
import com.example.eduardo.tecsa.ui.screen.movieList.MovieListScreen
import com.example.eduardo.tecsa.ui.theme.TecsaTheme

@Composable
fun AppScreen() {
    TecsaTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar()
        }) { innerPadding ->
            MovieListScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}