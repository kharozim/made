package com.ozimos.core.data.local

import com.ozimos.core.data.local.entities.MovieEntity
import com.ozimos.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(private val dao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> {
        return dao.getAllMovie()
    }

    fun getFavMovie(): Flow<List<MovieEntity>> {
        return dao.getFavMovie()
    }

    suspend fun updateFav(id: Int, isFavorite: Boolean) {
        dao.setFavorite(id, isFavorite)
    }

    suspend fun insertMovie(items: List<MovieEntity>) {
        dao.add(entities = items)
    }
}