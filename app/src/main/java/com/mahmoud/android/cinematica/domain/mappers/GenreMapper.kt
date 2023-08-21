package com.mahmoud.android.cinematica.domain.mappers

import com.mahmoud.android.cinematica.data.remote.response.genre.GenreDto
import com.mahmoud.android.cinematica.domain.models.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreDto, Genre> {
    override fun map(input: GenreDto): Genre {
        return Genre(
            input.id ?: 0,
            input.name ?: "unknown"
        )
    }
}