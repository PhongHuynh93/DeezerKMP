package com.wind.deezerkmp.shared.domain.model

import com.wind.deezerkmp.shared.Parcelable
import com.wind.deezerkmp.shared.Parcelize

/**
 * Created by Phong Huynh on 11/9/2020
 */
@Parcelize
data class Genre(
    val model: DeezerBaseModel,
) : Parcelable {
    fun isValid() = model.isValid()
}