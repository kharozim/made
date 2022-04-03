package com.ozimos.core.data.remote.service

import com.ozimos.core.data.remote.response.BaseResponse
import com.ozimos.core.data.remote.response.MovieResponse
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular")
    suspend fun getMovie(): BaseResponse<List<MovieResponse>>
}