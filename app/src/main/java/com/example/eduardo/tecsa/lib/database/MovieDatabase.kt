package com.example.eduardo.tecsa.lib.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eduardo.tecsa.App
import com.example.eduardo.tecsa.domain.model.TMDBMovie

interface IMovieDatabase {
    fun movieDao(): MovieDAO
}

@Database(entities = [TMDBMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(), IMovieDatabase {
    abstract override fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getDatabase(context: Context = App.applicationContext()): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, MovieDatabase::class.java, "movie_database"
                ).fallbackToDestructiveMigration().addCallback(roomCallback).build()
                INSTANCE = instance
                instance
            }
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}