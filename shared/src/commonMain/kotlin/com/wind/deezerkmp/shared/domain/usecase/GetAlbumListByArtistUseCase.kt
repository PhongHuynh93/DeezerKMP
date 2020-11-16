package com.wind.deezerkmp.shared.domain.usecase

import com.wind.deezerkmp.shared.data.Repository
import com.wind.deezerkmp.shared.domain.UseCase
import com.wind.deezerkmp.shared.domain.mapper.impl.AlbumDataMapper
import com.wind.deezerkmp.shared.domain.mapper.impl.ArtistDataMapper
import com.wind.deezerkmp.shared.domain.mapper.impl.GenreDataMapper
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.Artist
import com.wind.deezerkmp.shared.domain.model.Genre
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Phong Huynh on 11/9/2020
 */
class GetAlbumListByArtistParam(val id: String)
class GetAlbumListByArtistUseCase constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: Repository
) : UseCase<GetAlbumListByArtistParam, List<Album>>(dispatcher) {
    override suspend fun execute(parameters: GetAlbumListByArtistParam): List<Album> {
        return repository.getArtistAlbumList(parameters.id).data
            .map {
                AlbumDataMapper().map(it)
            }.filter {
                it.isValid()
            }
    }
}