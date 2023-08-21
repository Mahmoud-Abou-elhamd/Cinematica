package com.mahmoud.android.cinematica.data.remote.response

import com.google.gson.annotations.SerializedName

data class CollectionDto(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null
)
