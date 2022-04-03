package com.ozimos.core.domain.repository

import com.ozimos.core.data.Resourse
import com.ozimos.core.domain.models.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getAllMovie(): Flow<Resourse<List<MovieDomain>>>
    fun getFavMovie(): Flow<List<MovieDomain>>
    suspend fun setFav(id: Int, isFavorite: Boolean)

}