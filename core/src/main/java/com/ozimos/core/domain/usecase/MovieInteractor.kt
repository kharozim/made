package com.ozimos.core.domain.usecase

import com.ozimos.core.data.Resourse
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.core.domain.repository.MovieRepo
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: MovieRepo) : MovieUseCase {
    override fun getListMovie(): Flow<Resourse<List<MovieDomain>>> {
        return repository.getAllMovie()
    }

    override fun getFavoriteMovie(): Flow<List<MovieDomain>> {
        return repository.getFavMovie()
    }

    override suspend fun updateFavorite(id: Int, isFavorite: Boolean) {
        return repository.setFav(id, isFavorite)
    }
}