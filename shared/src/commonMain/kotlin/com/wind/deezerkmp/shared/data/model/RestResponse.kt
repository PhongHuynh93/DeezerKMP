package com.wind.deezerkmp.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Phong Huynh on 11/5/2020
 */
@Serializable
data class RestResponse<out T>(
    @SerialName("data")
    val data: List<T> = emptyList(),
    @SerialName("next")
    val next: String?,
    @SerialName("total")
    val total: Int?
)