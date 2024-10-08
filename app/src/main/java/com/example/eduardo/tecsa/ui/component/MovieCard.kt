package com.example.eduardo.tecsa.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduardo.tecsa.R
import com.example.eduardo.tecsa.domain.model.TMDBMovie
import com.example.eduardo.tecsa.lib.api.TMDBApi

@Composable
fun MovieCard(
    movie: TMDBMovie
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            MoviePoster(
                imageURL = TMDBApi.IMAGE_BASE_URL + movie.posterPath,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = movie.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1F)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "%.1f".format(movie.voteAverage),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(R.drawable.baseline_star_24),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                ExpandableText(text = movie.overview, minLines = 5)
            }
        }
    }
}
