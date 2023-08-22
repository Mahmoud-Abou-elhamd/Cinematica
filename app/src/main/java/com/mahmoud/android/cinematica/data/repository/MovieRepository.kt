package com.mahmoud.android.cinematica.data.repository

import com.mahmoud.android.cinematica.domain.models.Actor
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.domain.models.MovieDetails

interface MovieRepository {
    suspend fun getMovieGenreList(): List<Genre>

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovieListByGenreID(genreID: Int): List<Movie>

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieCast(movieId: Int): List<Actor>

    suspend fun getSimilarMovie(movieId: Int): List<Movie>
}