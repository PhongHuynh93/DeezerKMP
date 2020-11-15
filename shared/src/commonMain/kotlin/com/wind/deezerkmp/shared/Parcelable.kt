package com.wind.deezerkmp.shared

/**
 * Created by Phong Huynh on 10/5/2020
 */
expect interface Parcelable

// Common Code
@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()
