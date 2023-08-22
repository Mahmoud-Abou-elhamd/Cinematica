package com.mahmoud.android.cinematica.data.repository

import com.mahmoud.android.cinematica.data.remote.service.MovieService
import com.mahmoud.android.cinematica.domain.mappers.*
import com.mahmoud.android.cinematica.domain.models.Actor
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.domain.models.MovieDetails
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieService: MovieService,
    private val genreMapper: GenreMapper,
    private val movieMapper: MovieMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val actorMapper: ActorMapper,
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

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return wrap({ movieService.getMovieDetails(movieId) }, { movieDetailsMapper.map(it) })
    }

    override suspend fun getMovieCast(movieId: Int): List<Actor> {
        return wrap({ movieService.getMovieCast(movieId) }, { response ->
            ListMapper(actorMapper).mapList(response.cast)
        })
    }

    override suspend fun getSimilarMovie(movieId: Int): List<Movie> {
        return wrap({ movieService.getSimilarMovie(movieId) }, { response ->
            ListMapper(movieMapper).mapList(response.items)
        })
    }
}