package com.wind.deezerkmp.shared.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArtistComment(
    @SerialName("author")
    val author: NetworkAuthor?,
    @SerialName("date")
    val date: Int?,
    @SerialName("id")
    val id: String?,
    @SerialName("text")
    val text: String?,
    @SerialName("type")
    val type: String?
)
