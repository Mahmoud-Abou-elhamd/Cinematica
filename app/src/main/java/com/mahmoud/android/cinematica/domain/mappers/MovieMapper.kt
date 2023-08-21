package com.mahmoud.android.cinematica.domain.mappers

import com.mahmoud.android.cinematica.BuildConfig
import com.mahmoud.android.cinematica.data.remote.response.MovieDto
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.utilities.Constants
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieDto, Movie> {
    override fun map(input: MovieDto): Movie {
        return Movie(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            Constants.MOVIE,
            input.originalTitle ?: "",
            input.releaseDate?.substringBefore('-') ?: "",
            input.voteAverage?.toFloat() ?: 0f
        )
    }
}