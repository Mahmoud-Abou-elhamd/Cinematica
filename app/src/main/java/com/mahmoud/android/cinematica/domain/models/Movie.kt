package com.mahmoud.android.cinematica.domain.models

data class Movie(
    val movieID: Int,
    val movieImage: String,
    val movieType:String,
    val movieName: String,
    val movieDate: String,
    val movieRate: Float,
)
