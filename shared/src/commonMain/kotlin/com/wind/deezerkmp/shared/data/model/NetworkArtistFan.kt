package com.wind.deezerkmp.shared.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// FIXME: 11/5/2020 fan, author, artist share the same class
@Serializable
data class NetworkArtistFan(
    @SerialName("id")
    val id: String?,
    @SerialName("link")
    val link: String?,
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
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)
