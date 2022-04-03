package com.ozimos.core.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozimos.core.domain.models.MovieDomain

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val language: String,
    val popularity: Double,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val overview: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
) {
    fun toDomain() = MovieDomain(
        id,
        name,
        image,
        rating = rating,
        language = language,
        popularity = popularity,
        releaseDate = releaseDate,
        overview = overview,
        isFavorite = isFavorite
    )
}