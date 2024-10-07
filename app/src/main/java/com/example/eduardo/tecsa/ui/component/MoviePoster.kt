package com.example.eduardo.tecsa.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eduardo.tecsa.R

@Composable
fun MoviePoster(imageURL: String) {
    AsyncImage(
        model = imageURL,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.placeholder_image),
        placeholder = painterResource(R.drawable.placeholder_image),
        modifier = Modifier
            .width(128.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}