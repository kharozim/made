package com.ozimos.made.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.core.data.Resourse
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.core.domain.repository.MovieRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: MovieRepo) : ViewModel() {

    private val _list = MutableLiveData<Resourse<List<MovieDomain>>>()
    val list: LiveData<Resourse<List<MovieDomain>>>
        get() = _list

    init {
        getListMovie()
    }

    private fun getListMovie() {
        viewModelScope.launch {
            repo.getAllMovie().collect {
                _list.postValue(it)
            }
        }
    }

}