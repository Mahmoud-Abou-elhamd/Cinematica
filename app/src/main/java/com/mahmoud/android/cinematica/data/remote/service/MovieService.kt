package com.mahmoud.android.cinematica.data.remote.service

import com.mahmoud.android.cinematica.data.remote.response.BaseResponse
import com.mahmoud.android.cinematica.data.remote.response.CreditsDto
import com.mahmoud.android.cinematica.data.remote.response.MovieDetailsDto
import com.mahmoud.android.cinematica.data.remote.response.MovieDto
import com.mahmoud.android.cinematica.data.remote.response.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("genre/movie/list")
    suspend fun getGenreList(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getAllMovies(): Response<BaseResponse<MovieDto>>

    @GET("discover/movie")
    suspend fun getMovieListByGenre(
        @Query("with_genres") genreID: Int
    ): Response<BaseResponse<MovieDto>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movieId: Int): Response<CreditsDto>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(@Path("movie_id") movieId: Int): Response<BaseResponse<MovieDto>>
}