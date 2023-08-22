package com.mahmoud.android.cinematica.ui.movieDetails

import com.mahmoud.android.cinematica.domain.models.Actor
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.domain.models.MovieDetails

sealed class DetailItem(val priority: Int){
    class Header(val data: MovieDetails) : DetailItem(0)

    class Cast(val data: List<Actor>) : DetailItem(1)

    class SimilarMovies(val data: List<Movie>) : DetailItem(2)
}
