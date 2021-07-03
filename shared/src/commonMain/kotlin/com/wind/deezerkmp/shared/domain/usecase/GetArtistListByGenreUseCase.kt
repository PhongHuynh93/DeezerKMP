package com.wind.deezerkmp.shared.domain.usecase

import com.wind.deezerkmp.shared.data.Repository
import com.wind.deezerkmp.shared.domain.UseCase
import com.wind.deezerkmp.shared.domain.mapper.impl.ArtistDataMapper
import com.wind.deezerkmp.shared.domain.model.Artist
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Phong Huynh on 11/9/2020
 */
class GetArtistListByGenreParam(val id: String)
class GetArtistListByGenreUseCase constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<GetArtistListByGenreParam, List<Artist>>(dispatcher) {
    override suspend fun execute(parameters: GetArtistListByGenreParam): List<Artist> {
        return repository.getArtistByGenreList(parameters.id).data
            .map {
                ArtistDataMapper().map(it)
            }.filter {
                it.isValid()
            }
    }
}