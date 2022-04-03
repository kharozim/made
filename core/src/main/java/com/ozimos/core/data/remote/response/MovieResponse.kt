package com.ozimos.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.ozimos.core.data.local.entities.MovieEntity

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title", alternate = ["name"])
    val name: String? = null,

    @field:SerializedName("backdrop_path")
    var image: String? = null,

    @field:SerializedName("vote_average")
    val rating: Double? = null,

    @field:SerializedName("original_language")
    val language: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,
)

fun MovieResponse.toEntity() = MovieEntity(
    id,
    name ?: "",
    image ?: "",
    rating ?: 0.0,
    language ?: "",
    popularity ?: 0.0,
    releaseDate ?: "",
    overview ?: "",
    false
)