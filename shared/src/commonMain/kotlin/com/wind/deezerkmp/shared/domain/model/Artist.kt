package com.wind.deezerkmp.shared.domain.model


/**
 * Created by Phong Huynh on 11/5/2020
 */
data class Artist(
    private val person: PersonModel,
    private val picture: String,
    private val pictureBig: String,
    private val pictureMedium: String,
    private val pictureSmall: String,
    private val pictureXl: String,
    private val radio: Boolean,
) : Person {
    override fun getPerson() = person
}