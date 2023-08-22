package com.mahmoud.android.cinematica.ui.home

import com.mahmoud.android.cinematica.R
import com.mahmoud.android.cinematica.domain.models.Movie
import com.mahmoud.android.cinematica.ui.base.BaseAdapter
import com.mahmoud.android.cinematica.ui.base.BaseInteractionListener

class MovieAdapter(items: List<Movie>, val layout: Int, listener: MovieInteractionListener) : BaseAdapter<Movie>(items, listener){
    override val layoutID = layout
}

interface MovieInteractionListener : BaseInteractionListener {
    fun onClickMovie(movieId: Int)
}