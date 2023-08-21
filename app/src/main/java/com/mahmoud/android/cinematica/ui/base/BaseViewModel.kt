package com.mahmoud.android.cinematica.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun wrapWithState(function: suspend () -> Unit, errorFunction: (e: Throwable) -> Unit = {}) {
        viewModelScope.launch {
            try {
                function()
            } catch (e: Throwable) {
                errorFunction(e)
            }
        }
    }

    abstract fun getData()
}