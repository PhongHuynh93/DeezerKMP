package com.wind.deezerkmp.shared.di

import com.wind.deezerkmp.shared.base.ioDispatcher
import com.wind.deezerkmp.shared.data.Repository
import com.wind.deezerkmp.shared.data.RepositoryImpl
import com.wind.deezerkmp.shared.domain.usecase.GetAlbumListByArtistUseCase
import com.wind.deezerkmp.shared.domain.usecase.GetArtistListByGenreUseCase
import com.wind.deezerkmp.shared.domain.usecase.GetGenreListUseCase
import com.wind.deezerkmp.shared.domain.usecase.GetTrackListInAlbumUseCase
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * Created by Phong Huynh on 11/4/2020
 */

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

// called by iOS etc
fun initKoin() = initKoin {}

val commonModule = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single<Repository> {
        val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(get())
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
        }
        RepositoryImpl(httpClient)
    }
    factory { GetGenreListUseCase(ioDispatcher, get()) }
    factory { GetArtistListByGenreUseCase(ioDispatcher, get()) }
    factory { GetAlbumListByArtistUseCase(ioDispatcher, get()) }
    factory { GetTrackListInAlbumUseCase(ioDispatcher, get()) }
}