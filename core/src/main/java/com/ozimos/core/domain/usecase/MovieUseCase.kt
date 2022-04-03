package com.ozimos.core.domain.usecase

import com.ozimos.core.data.Resourse
import com.ozimos.core.domain.models.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListMovie(): Flow<Resourse<List<MovieDomain>>>
    fun getFavoriteMovie(): Flow<List<MovieDomain>>
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)
}