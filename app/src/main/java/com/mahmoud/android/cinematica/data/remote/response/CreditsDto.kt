package com.mahmoud.android.cinematica.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreditsDto(
    @SerializedName("cast")
    val cast: List<ActorDto>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0
)
