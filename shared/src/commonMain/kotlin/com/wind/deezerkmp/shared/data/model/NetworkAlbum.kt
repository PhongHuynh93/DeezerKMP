package com.wind.deezerkmp.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAlbum(
    @SerialName("cover")
    val cover: String?,
    @SerialName("cover_big")
    val coverBig: String?,
    @SerialName("cover_medium")
    val coverMedium: String?,
    @SerialName("cover_small")
    val coverSmall: String?,
    @SerialName("cover_xl")
    val coverXl: String?,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerialName("fans")
    val fans: Int?,
    @SerialName("genre_id")
    val genreId: Int?,
    @SerialName("id")
    val id: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("record_type")
    val recordType: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)
