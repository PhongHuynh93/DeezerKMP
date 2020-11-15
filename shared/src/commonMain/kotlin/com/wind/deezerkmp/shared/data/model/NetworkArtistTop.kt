package com.wind.deezerkmp.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArtistTop(
    @SerialName("album")
    val album: NetworkAlbum?,
    @SerialName("artist")
    val artist: NetworkArtist?,
    @SerialName("contributors")
    val contributors: List<Contributor?>?,
    @SerialName("duration")
    val duration: String?,
    @SerialName("explicit_content_cover")
    val explicitContentCover: Int?,
    @SerialName("explicit_content_lyrics")
    val explicitContentLyrics: Int?,
    @SerialName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerialName("id")
    val id: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("preview")
    val preview: String?,
    @SerialName("rank")
    val rank: String?,
    @SerialName("readable")
    val readable: Boolean?,
    @SerialName("title")
    val title: String?,
    @SerialName("title_short")
    val titleShort: String?,
    @SerialName("title_version")
    val titleVersion: String?,
    @SerialName("type")
    val type: String?
)