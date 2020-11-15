package com.wind.animelist.shared.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Phong Huynh on 10/6/2020
 */
actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val clientScope: CoroutineScope = viewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}