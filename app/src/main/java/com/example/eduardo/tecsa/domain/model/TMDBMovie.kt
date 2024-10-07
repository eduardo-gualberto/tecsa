package com.example.eduardo.tecsa.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tmdb_movie")
data class TMDBMovie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double
) {}