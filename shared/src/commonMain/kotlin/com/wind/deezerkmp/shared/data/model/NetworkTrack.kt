package com.wind.deezerkmp.shared.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTrack(
    @SerialName("artist")
    val artist: NetworkArtist? = null,
    @SerialName("disk_number")
    val diskNumber: Int? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("explicit_content_cover")
    val explicitContentCover: Int? = null,
    @SerialName("explicit_content_lyrics")
    val explicitContentLyrics: Int? = null,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("isrc")
    val isrc: String? = null,
    @SerialName("link")
    val link: String? = null,
    @SerialName("md5_image")
    val md5Image: String? = null,
    @SerialName("preview")
    val preview: String? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("readable")
    val readable: Boolean? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("title_short")
    val titleShort: String? = null,
    @SerialName("title_version")
    val titleVersion: String? = null,
    @SerialName("track_position")
    val trackPosition: Int? = null,
    @SerialName("type")
    val type: String? = null
)