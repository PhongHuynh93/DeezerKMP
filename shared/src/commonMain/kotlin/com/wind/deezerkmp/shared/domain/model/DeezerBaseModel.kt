package com.wind.deezerkmp.shared.domain.model

import com.wind.deezerkmp.shared.Parcelable
import com.wind.deezerkmp.shared.Parcelize

/**
 * Created by Phong Huynh on 11/9/2020
 */
@Parcelize
data class DeezerBaseModel(
    val id: String,
    val name: String,
    val picture: String,
    val pictureBig: String,
    val pictureMedium: String,
    val pictureSmall: String,
    val pictureXl: String
) : Parcelable {
    fun isValid() = id.isNotEmpty()
}