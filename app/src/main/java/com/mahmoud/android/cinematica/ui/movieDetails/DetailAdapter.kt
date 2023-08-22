package com.mahmoud.android.cinematica.ui.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mahmoud.android.cinematica.ui.base.BaseAdapter
import com.mahmoud.android.cinematica.ui.base.BaseInteractionListener
import com.mahmoud.android.cinematica.BR
import com.mahmoud.android.cinematica.R
import com.mahmoud.android.cinematica.ui.home.MovieAdapter
import com.mahmoud.android.cinematica.ui.home.MovieInteractionListener

class DetailAdapter(
    private var items: List<DetailItem>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<DetailItem>(items, listener) {
    override val layoutID = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        when(val currentItem = items[position]){
            is DetailItem.Header -> {
                holder.binding.run {
                    setVariable(BR.item, currentItem.data)
                    setVariable(BR.listener, listener as DetailInteractionListener)
                }
            }
            is DetailItem.Cast -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler, ActorAdapter(currentItem.data, R.layout.item_cast, listener))
                }
            }
            is DetailItem.SimilarMovies -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler, MovieAdapter(currentItem.data, R.layout.item_similar_movie, listener as MovieInteractionListener))
                }
            }
        }
    }

    override fun setItems(newItems: List<DetailItem>) {
        items = newItems.sortedBy { it.priority }
        super.setItems(items)
    }

    override fun areItemsSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DetailItem.Header -> R.layout.item_movie_detail_header
            is DetailItem.Cast -> R.layout.list_cast
            is DetailItem.SimilarMovies -> R.layout.list_similar_movie
        }
    }
}