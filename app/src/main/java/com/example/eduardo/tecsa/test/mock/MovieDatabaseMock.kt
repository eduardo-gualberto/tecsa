package com.example.eduardo.tecsa.test.mock

import android.content.Context
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.eduardo.tecsa.lib.database.IMovieDatabase
import com.example.eduardo.tecsa.lib.database.MovieDAO
import com.example.eduardo.tecsa.lib.database.MovieDatabase

object MovieDatabaseMock: IMovieDatabase {
    override fun movieDao(): MovieDAO {
        return MovieDAOMock()
    }
}