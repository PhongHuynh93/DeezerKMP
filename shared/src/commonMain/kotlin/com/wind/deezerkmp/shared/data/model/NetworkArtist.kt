package com.wind.deezerkmp.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArtist(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("picture")
    val picture: String?,
    @SerialName("picture_big")
    val pictureBig: String?,
    @SerialName("picture_medium")
    val pictureMedium: String?,
    @SerialName("picture_small")
    val pictureSmall: String?,
    @SerialName("picture_xl")
    val pictureXl: String?,
    @SerialName("radio")
    val radio: Boolean?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)
