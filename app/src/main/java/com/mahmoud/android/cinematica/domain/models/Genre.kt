package com.mahmoud.android.cinematica.domain.models

import com.mahmoud.android.cinematica.utilities.Constants

data class Genre(
    val genreID: Int = Constants.FIRST_CATEGORY_ID,
    val genreName: String
)
