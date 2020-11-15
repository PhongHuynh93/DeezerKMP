package com.wind.deezerkmp.shared.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPlaylist(
    @SerialName("checksum")
    val checksum: String?,
    @SerialName("creation_date")
    val creationDate: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("link")
    val link: String?,
    @SerialName("md5_image")
    val md5Image: String?,
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
    @SerialName("public")
    val `public`: Boolean?,
    @SerialName("title")
    val title: String?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("user")
    val user: NetworkUser?
)
