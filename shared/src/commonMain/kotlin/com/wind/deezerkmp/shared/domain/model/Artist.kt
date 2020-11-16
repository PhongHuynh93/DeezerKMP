package com.wind.deezerkmp.shared.domain.model

import com.wind.deezerkmp.shared.base.Parcelable
import com.wind.deezerkmp.shared.base.Parcelize


/**
 * Created by Phong Huynh on 11/5/2020
 */
@Parcelize
data class Artist(
    val id: String,
    val model: DeezerBaseModel,
    val radio: Boolean,
    val trackList: String
): Parcelable {
    fun isValid() = model.isValid() && id.isNotEmpty()
}