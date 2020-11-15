package com.wind.deezerkmp.shared.domain.model

import kotlinx.serialization.SerialName

/**
 * Created by Phong Huynh on 11/5/2020
 */
interface Person {
    fun getPerson(): PersonModel
}

data class PersonModel(
    private val id: Int,
    private val name: String,
    private val trackList: String,
    private val type: String
)