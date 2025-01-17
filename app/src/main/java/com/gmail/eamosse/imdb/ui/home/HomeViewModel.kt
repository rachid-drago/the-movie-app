package com.gmail.eamosse.imdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Film
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories



    private val _films: MutableLiveData<List<Film>> = MutableLiveData()
    val films: LiveData<List<Film>>
        get() = _films



    fun getFilms(categoryId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getFilms(categoryId)) {
                is Result.Succes -> {
                    _films.postValue(result.data)

                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }


    private val _film: MutableLiveData<Film> = MutableLiveData()
    val film: LiveData<Film>
        get() = _film

    fun getFilm(filmId :Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getFilm(filmId)) {
                is Result.Succes -> {
                    _film.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }




    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}