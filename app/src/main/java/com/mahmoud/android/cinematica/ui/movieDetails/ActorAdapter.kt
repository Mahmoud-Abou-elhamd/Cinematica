package com.mahmoud.android.cinematica.ui.movieDetails

import com.mahmoud.android.cinematica.domain.models.Actor
import com.mahmoud.android.cinematica.ui.base.BaseAdapter
import com.mahmoud.android.cinematica.ui.base.BaseInteractionListener

class ActorAdapter(items: List<Actor>, val layout: Int, val listener: BaseInteractionListener) :
    BaseAdapter<Actor>(items, listener) {
    override val layoutID: Int = layout
}

//interface ActorsInteractionListener : BaseInteractionListener {
//    fun onClickActor(actorID: Int)
//}