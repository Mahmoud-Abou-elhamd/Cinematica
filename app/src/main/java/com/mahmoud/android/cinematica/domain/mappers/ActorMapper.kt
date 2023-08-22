package com.mahmoud.android.cinematica.domain.mappers

import com.mahmoud.android.cinematica.BuildConfig
import com.mahmoud.android.cinematica.data.remote.response.ActorDto
import com.mahmoud.android.cinematica.domain.models.Actor
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<ActorDto, Actor> {
    override fun map(input: ActorDto): Actor {
        return Actor(
            actorID = input.id ?: 0,
            actorName = input.name ?: "unknown",
            actorImage = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}