package com.wind.deezerkmp.shared.domain.mapper.impl

import com.wind.deezerkmp.shared.data.model.NetworkArtist
import com.wind.deezerkmp.shared.data.model.NetworkGenre
import com.wind.deezerkmp.shared.domain.mapper.Mapper
import com.wind.deezerkmp.shared.domain.model.Artist
import com.wind.deezerkmp.shared.domain.model.DeezerBaseModel
import com.wind.deezerkmp.shared.domain.model.Genre

/**
 * Created by Phong Huynh on 11/9/2020
 */
class ArtistDataMapper : Mapper<NetworkArtist, Artist> {
    override fun map(input: NetworkArtist): Artist {
        return Artist(
            id = input.id.orEmpty(),
            model =
            DeezerBaseModel(
                name = input.name.orEmpty(),
                picture = input.picture.orEmpty(),
                pictureBig = input.pictureBig.orEmpty(),
                pictureMedium = input.pictureMedium.orEmpty(),
                pictureSmall = input.pictureSmall.orEmpty(),
                pictureXl = input.pictureXl.orEmpty(),
            ),
            radio = input.radio ?: false,
            trackList = input.tracklist.orEmpty()
        )
    }
}