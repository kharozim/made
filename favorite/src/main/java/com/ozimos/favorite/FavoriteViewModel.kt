package com.ozimos.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.core.domain.repository.MovieRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repo: MovieRepo) : ViewModel() {

    private val _list = MutableLiveData<List<MovieDomain>>()
    val list: LiveData<List<MovieDomain>>
        get() = _list

    init {
        getListMovie()
    }

    private fun getListMovie() {
        viewModelScope.launch {
            repo.getFavMovie().collect {
                _list.postValue(it)
            }
        }
    }

}