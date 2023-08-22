package com.mahmoud.android.cinematica.domain.mappers

import com.mahmoud.android.cinematica.BuildConfig
import com.mahmoud.android.cinematica.data.remote.response.MovieDetailsDto
import com.mahmoud.android.cinematica.domain.models.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            input.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            input.title ?: "",
            input.releaseDate?.take(4) ?: "unknown",
            input.genres?.map { it?.name }?.joinToString(" , ") ?: "unknown",
            input.runtime ?: 0,
            input.voteCount ?: 0,
            input.voteAverage.toString().take(3),
            input.overview ?: ""
        )
    }
}