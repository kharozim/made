package com.ozimos.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozimos.core.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getFavMovie(): Flow<List<MovieEntity>>

    @Query("UPDATE movies SET is_favorite = :state WHERE id = :id")
    suspend fun setFavorite(id: Int, state: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entities: List<MovieEntity>)
}