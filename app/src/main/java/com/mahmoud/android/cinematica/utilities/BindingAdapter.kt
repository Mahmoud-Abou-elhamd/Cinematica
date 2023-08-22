package com.mahmoud.android.cinematica.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.ChipGroup
import com.mahmoud.android.cinematica.R
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.domain.models.MovieDetails
import com.mahmoud.android.cinematica.ui.UIState
import com.mahmoud.android.cinematica.ui.base.BaseAdapter
import com.mahmoud.android.cinematica.utilities.Constants.ALL
import com.mahmoud.android.cinematica.utilities.Constants.FIRST_CATEGORY_ID

@BindingAdapter("app:setGenres", "app:listener", "app:firstChipSelection")
fun <T> setGenresChips(
    view: ChipGroup,
    chipList: UIState<List<Genre>>?,
    listener: T,
    isFirstChipSelected: Boolean?
) {
    val allMedia = Genre(FIRST_CATEGORY_ID, ALL)
    chipList?.toData()?.let {
        view.addView(view.createChip(allMedia, listener))
        it.forEach { genre -> view.addView(view.createChip(genre, listener)) }
    }

    if (isFirstChipSelected == true) view.getChildAt(FIRST_CATEGORY_ID)?.id?.let { view.check(it) }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
    view.scrollToPosition(0)
}

@BindingAdapter("app:posterImage")
fun bindMovieImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.ic_baseline_person_24)
        }
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: UIState<T>?) {
    view.isVisible = (state is UIState.Loading)
}

@BindingAdapter("app:showWhenFail")
fun <T> showWhenFail(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Error
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, state: UIState<T>?) {
    view.isVisible = state is UIState.Success
}

@BindingAdapter("app:setReleaseDate")
fun setReleaseDate(text: TextView, date: String?) {
    text.text = date?.take(4)
}

@BindingAdapter("app:duration")
fun setDuration(view: TextView, movieDetails: MovieDetails?) {
    movieDetails?.let {
        view.text = view.context.getString(R.string.duration, movieDetails.movieDuration)
    }
}
