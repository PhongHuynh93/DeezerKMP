package com.wind.deezerkmp.shared.domain.mapper.impl

import com.wind.deezerkmp.shared.data.model.NetworkTrack
import com.wind.deezerkmp.shared.domain.mapper.Mapper
import com.wind.deezerkmp.shared.domain.model.DeezerBaseModel
import com.wind.deezerkmp.shared.domain.model.Track

/**
 * Created by Phong Huynh on 11/16/2020
 */
class TrackDataMapper: Mapper<NetworkTrack, Track> {
    override fun map(input: NetworkTrack): Track {
        return Track(
            id = input.id.orEmpty(),
            model =
            DeezerBaseModel(
                name = input.title.orEmpty(),
                picture = "",
                pictureBig = "",
                pictureMedium = "",
                pictureSmall = "",
                pictureXl = "",
            ),
            duration = input.duration ?: 0
        )
    }
}