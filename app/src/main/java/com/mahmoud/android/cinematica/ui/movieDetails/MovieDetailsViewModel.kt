package com.mahmoud.android.cinematica.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mahmoud.android.cinematica.data.repository.MovieRepository
import com.mahmoud.android.cinematica.domain.models.MovieDetails
import com.mahmoud.android.cinematica.ui.UIState
import com.mahmoud.android.cinematica.ui.base.BaseViewModel
import com.mahmoud.android.cinematica.ui.home.MovieInteractionListener
import com.mahmoud.android.cinematica.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    state: SavedStateHandle
) : BaseViewModel(), DetailInteractionListener, MovieInteractionListener {
    private val args = MovieDetailsFragmentArgs.fromSavedStateHandle(state)

    private var _movieDetails = MutableLiveData<UIState<MovieDetails>>()
    val movieDetails: LiveData<UIState<MovieDetails>> = _movieDetails

    val detailItemsLiveData = MutableLiveData<UIState<List<DetailItem>>>()
    private val detailItems = mutableListOf<DetailItem>()

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val _clickMovieEvent = MutableLiveData<Event<Int>>()
    val clickMovieEvent: LiveData<Event<Int>> = _clickMovieEvent

    init {
        getData()
    }

    override fun getData() {
        getAllDetails(args.movieId)
    }

    private fun getAllDetails(movieId: Int){
        detailItemsLiveData.postValue(UIState.Loading)
        getMovieDetails(movieId)
        getMovieCast(movieId)
        getSimilarMovie(movieId)
    }

    private fun getMovieDetails(movieId: Int){
        wrapWithState({
            val response = movieRepository.getMovieDetails(movieId)
            updateDetailItems(DetailItem.Header(response))
        },{
            detailItemsLiveData.postValue(UIState.Error(""))
        })
    }

    private fun getMovieCast(movieId: Int) {
        wrapWithState({
            val response = movieRepository.getMovieCast(movieId)
            updateDetailItems(DetailItem.Cast(response))
        })
    }

    private fun getSimilarMovie(movieId: Int) {
        wrapWithState(
            {
                val response = movieRepository.getSimilarMovie(movieId)
                updateDetailItems(DetailItem.SimilarMovies(response))
            }
        )
    }

    private fun updateDetailItems(item: DetailItem) {
        detailItems.add(item)
        detailItemsLiveData.postValue(UIState.Success(detailItems))
    }

    override fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onClickMovie(movieId: Int) {
        _clickMovieEvent.postValue(Event(movieId))
    }
}