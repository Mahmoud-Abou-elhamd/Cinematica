package com.mahmoud.android.cinematica.data.remote.response.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreDto>?
)
