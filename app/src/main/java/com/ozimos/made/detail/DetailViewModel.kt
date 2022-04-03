package com.ozimos.made.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.core.domain.repository.MovieRepo
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: MovieRepo) : ViewModel() {

    fun setFavMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            repo.setFav(id, isFavorite)
        }
    }
}