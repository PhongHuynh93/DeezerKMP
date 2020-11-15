package com.wind.deezerkmp.shared.domain.model

/**
 * Created by Phong Huynh on 11/5/2020
 */
data class User(private val person: PersonModel): Person {
    override fun getPerson() = person
}