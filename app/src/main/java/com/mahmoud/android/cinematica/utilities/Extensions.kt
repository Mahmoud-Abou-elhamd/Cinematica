package com.mahmoud.android.cinematica.utilities

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.chip.ChipGroup
import com.mahmoud.android.cinematica.R
import com.mahmoud.android.cinematica.databinding.ItemChipCategoryBinding
import com.mahmoud.android.cinematica.domain.models.Genre
import com.mahmoud.android.cinematica.ui.home.CategoryInteractionListener

fun <T> ChipGroup.createChip(item: Genre, listener: T): View {
    val chipBinding: ItemChipCategoryBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.item_chip_category,
        this,
        false
    )
    chipBinding.item = item
    chipBinding.listener = listener as CategoryInteractionListener
    return chipBinding.root
}

inline fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}