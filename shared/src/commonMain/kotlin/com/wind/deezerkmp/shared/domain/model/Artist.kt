package com.wind.deezerkmp.shared.domain.model


/**
 * Created by Phong Huynh on 11/5/2020
 */
data class Artist(
    private val id: String,
    private val model: DeezerBaseModel,
    private val radio: Boolean,
    private val trackList: String
) {
    fun isValid() = model.isValid() && id.isNotEmpty()
}