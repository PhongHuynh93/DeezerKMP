package com.wind.deezerkmp.shared.domain.model


/**
 * Created by Phong Huynh on 11/5/2020
 */
data class Artist(
    val id: String,
    val model: DeezerBaseModel,
    val radio: Boolean,
    val trackList: String
) {
    fun isValid() = model.isValid() && id.isNotEmpty()
}