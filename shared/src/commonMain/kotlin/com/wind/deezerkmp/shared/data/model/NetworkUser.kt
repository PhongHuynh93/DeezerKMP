package com.wind.deezerkmp.shared.data.model

import com.wind.deezerkmp.shared.domain.model.Person
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Phong Huynh on 11/5/2020
 */
@Serializable
data class NetworkUser(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("tracklist")
    val tracklist: String?,
    @SerialName("type")
    val type: String?
)