package com.wind.deezerkmp.shared.domain.model

import com.wind.deezerkmp.shared.base.Parcelable
import com.wind.deezerkmp.shared.base.Parcelize

/**
 * Created by Phong Huynh on 11/19/2020
 */
@Parcelize
data class Track(
    val id: String,
    val model: DeezerBaseModel,
    val duration: Int,
    val artist: Artist,
    val album: Album
) : Parcelable {
    fun isValid() = model.isValid() && id.isNotEmpty()
}