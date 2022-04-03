package com.ozimos.core.data

import com.ozimos.core.data.local.MovieLocalDataSource
import com.ozimos.core.data.remote.ApiResponse
import com.ozimos.core.data.remote.MovieRemoteDataSource
import com.ozimos.core.data.remote.response.MovieResponse
import com.ozimos.core.data.remote.response.toEntity
import com.ozimos.core.domain.repository.MovieRepo
import com.ozimos.core.domain.models.MovieDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepoImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepo {
    override fun getAllMovie(): Flow<Resourse<List<MovieDomain>>> {
        return object : NetworkResourceBound<List<MovieDomain>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieDomain>> {
                val entity = localDataSource.getAllMovie()
                val domain = entity.map {
                    it.asSequence().map { item -> item.toDomain() }.toList()
                }
                return domain
            }

            override fun shouldFetch(data: List<MovieDomain>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val toEntity = data.asSequence().map { it.toEntity() }.toList()
                return localDataSource.insertMovie(toEntity)
            }

        }.asFlow()


    }

    override fun getFavMovie(): Flow<List<MovieDomain>> {
        val entity = localDataSource.getFavMovie()
        return entity.map { it.asSequence().map { item -> item.toDomain() }.toList() }
    }

    override suspend fun setFav(id: Int, isFavorite: Boolean) {
        return localDataSource.updateFav(id, isFavorite)
    }

}