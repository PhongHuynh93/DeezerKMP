package com.wind.deezerkmp.shared.domain.mapper.impl

import com.wind.deezerkmp.shared.data.model.NetworkAlbum
import com.wind.deezerkmp.shared.domain.mapper.Mapper
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.DeezerBaseModel

/**
 * Created by Phong Huynh on 11/16/2020
 */
class AlbumDataMapper: Mapper<NetworkAlbum, Album> {
    override fun map(input: NetworkAlbum): Album {
        return Album(
            id = input.id.orEmpty(),
            model =
            DeezerBaseModel(
                name = input.title.orEmpty(),
                picture = input.cover.orEmpty(),
                pictureBig = input.coverBig.orEmpty(),
                pictureMedium = input.coverMedium.orEmpty(),
                pictureSmall = input.coverSmall.orEmpty(),
                pictureXl = input.coverXl.orEmpty(),
            ),
            releaseDate = input.releaseDate.orEmpty()
        )
    }
}