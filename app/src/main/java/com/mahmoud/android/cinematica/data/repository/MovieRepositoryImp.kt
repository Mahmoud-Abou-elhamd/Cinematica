package com.mahmoud.android.cinematica.data.repository

import com.mahmoud.android.cinematica.data.remote.service.MovieService
import com.mahmoud.android.cinematica.domain.mappers.GenreMapper
import com.mahmoud.android.cinematica.domain.mappers.ListMapper
import com.mahmoud.android.cinematica.domain.mappers.MovieMapper
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.Movie
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val genreMapper: GenreMapper,
    private val movieMapper: MovieMapper,
) : BaseMovieRepository(), MovieRepository {
    override suspend fun getMovieGenreList(): List<Genre> {
        return wrap({movieService.getGenreList()},
            {ListMapper(genreMapper).mapList(it.genres)})
    }

    override suspend fun getAllMovies(): List<Movie> {
        return wrap({movieService.getAllMovies()},
            {ListMapper(movieMapper).mapList(it.items)})
    }

    override suspend fun getMovieListByGenreID(genreID: Int): List<Movie> {
        return wrap({movieService.getMovieListByGenre(genreID)},
            {ListMapper(movieMapper).mapList(it.items)})
    }
}