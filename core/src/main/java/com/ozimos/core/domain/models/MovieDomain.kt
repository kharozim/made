package com.ozimos.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDomain(
    val id: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean,
    val rating: Double,
    val language: String,
    val popularity: Double,
    val releaseDate: String,
    val overview: String,
) : Parcelable
