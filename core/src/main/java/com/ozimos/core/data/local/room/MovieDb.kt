package com.ozimos.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozimos.core.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}