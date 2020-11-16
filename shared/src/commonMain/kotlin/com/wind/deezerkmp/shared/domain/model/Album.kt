package com.wind.deezerkmp.shared.domain.model

import com.wind.deezerkmp.shared.base.Parcelable
import com.wind.deezerkmp.shared.base.Parcelize

/**
 * Created by Phong Huynh on 11/16/2020
 */
@Parcelize
data class Album(
    val id: String,
    val model: DeezerBaseModel,
): Parcelable {
    fun isValid() = model.isValid() && id.isNotEmpty()
}