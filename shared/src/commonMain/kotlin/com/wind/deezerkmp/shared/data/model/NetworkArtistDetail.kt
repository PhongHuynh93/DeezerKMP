package com.wind.deezerkmp.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArtistDetail(
    @SerialName("id")
    val id: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("nb_album")
    val nbAlbum: Int?,
    @SerialName("nb_fan")
    val nbFan: Int?,
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
    @SerialName("share")
    val share: String?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)