package com.example.eduardo.tecsa.lib.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eduardo.tecsa.domain.model.TMDBMovie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(movieList: List<TMDBMovie>)

    @Query("select * from tmdb_movie")
    fun getAll(): List<TMDBMovie>
}