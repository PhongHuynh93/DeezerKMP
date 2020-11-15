package com.wind.animelist.shared.base

import timber.log.Timber

/**
 * Created by Phong Huynh on 11/1/2020
 */
actual fun log(message: String) {
    Timber.e(message)
}