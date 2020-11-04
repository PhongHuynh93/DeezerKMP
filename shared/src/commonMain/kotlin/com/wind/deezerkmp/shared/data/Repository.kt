package com.wind.deezerkmp.shared.data

import io.ktor.client.*

/**
 * Created by Phong Huynh on 11/4/2020
 */
interface Repository {
}

internal class RepositoryImpl internal constructor(private val client: HttpClient) : Repository {

}