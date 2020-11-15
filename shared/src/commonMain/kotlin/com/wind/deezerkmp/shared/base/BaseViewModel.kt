package com.wind.animelist.shared.base

import kotlinx.coroutines.CoroutineScope

/**
 * Created by Phong Huynh on 10/6/2020
 * how to share viewmodel
 * https://proandroiddev.com/maximizing-code-sharing-between-android-and-ios-with-kotlin-multiplatform-85d58d0a2cf1
 */
expect open class BaseViewModel() {
    val clientScope: CoroutineScope
    protected open fun onCleared()
}