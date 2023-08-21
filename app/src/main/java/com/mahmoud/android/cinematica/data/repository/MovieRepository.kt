package com.mahmoud.android.cinematica.data.repository

import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.Movie

interface MovieRepository {
    suspend fun getMovieGenreList(): List<Genre>

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovieListByGenreID(genreID: Int): List<Movie>
}