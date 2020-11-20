package com.wind.deezerkmp.shared.domain.usecase

import com.wind.deezerkmp.shared.data.Repository
import com.wind.deezerkmp.shared.domain.UseCase
import com.wind.deezerkmp.shared.domain.mapper.impl.TrackDataMapper
import com.wind.deezerkmp.shared.domain.model.Track
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Phong Huynh on 11/19/2020
 */
class GetTrackListInAlbumParam(val id: String)
class GetTrackListInAlbumUseCase constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<GetTrackListInAlbumParam, List<Track>>(dispatcher) {
    override suspend fun execute(parameters: GetTrackListInAlbumParam): List<Track> {
        return repository.getTrackListInAlbum(parameters.id).data
            .map {
                TrackDataMapper().map(it)
            }.filter {
                it.isValid()
            }
    }
}