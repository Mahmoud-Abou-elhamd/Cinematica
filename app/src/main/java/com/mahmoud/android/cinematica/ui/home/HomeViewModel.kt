package com.mahmoud.android.cinematica.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahmoud.android.cinematica.data.repository.MovieRepository
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.ui.UIState
import com.mahmoud.android.cinematica.ui.base.BaseViewModel
import com.mahmoud.android.cinematica.utilities.Constants.ALL
import com.mahmoud.android.cinematica.utilities.Constants.FIRST_CATEGORY_ID
import com.mahmoud.android.cinematica.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(), MovieInteractionListener, CategoryInteractionListener {

    private val _categories = MutableLiveData<UIState<List<Genre>>>()
    val categories: LiveData<UIState<List<Genre>>> = _categories

    private val _movieList = MutableLiveData<UIState<List<Movie>>>()
    val movieList: LiveData<UIState<List<Movie>>> = _movieList

    private val _categoryId = MutableLiveData(FIRST_CATEGORY_ID)
    val categoryId: LiveData<Int> = _categoryId

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    var clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    init {
        getData()
    }

    override fun getData(){
        _movieList.postValue(UIState.Loading)
        getMovieGenres()
        getAllMovies()
    }

    private fun getMovieGenres(){
        _categories.postValue(UIState.Loading)
        wrapWithState({
            val response = setGenre(movieRepository.getMovieGenreList())
            _categories.postValue(UIState.Success(response))
        },{
            _categories.postValue(UIState.Error(it.message ?: ""))
        })
    }

    private fun setGenre(genre: List<Genre>): List<Genre> {
        val allGenre = mutableListOf<Genre>()
        allGenre.add(Genre(FIRST_CATEGORY_ID, ALL))
        allGenre.addAll(genre)
        return allGenre.toList()
    }

    private fun getAllMovies(){
        wrapWithState({
            val response = movieRepository.getAllMovies()
            _movieList.postValue(UIState.Success(response))
        },{
            _movieList.postValue(UIState.Error(it.message ?: ""))
        })
    }

    private fun getMoviesByGenre(id: Int){
        wrapWithState({
            val response = movieRepository.getMovieListByGenreID(id)
            _movieList.postValue(UIState.Success(response))
        },{
            _movieList.postValue(UIState.Error(it.message ?: ""))
        })
    }

    override fun onClickCategory(categoryId: Int) {
        _categoryId.postValue(categoryId)
        when(categoryId){
            FIRST_CATEGORY_ID -> getAllMovies()
            else -> getMoviesByGenre(categoryId)
        }
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postValue(Event(movieId))
    }
}