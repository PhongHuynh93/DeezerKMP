package com.wind.deezerkmp.androidApp.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wind.deezerkmp.androidApp.ui.home.GenreTitleModel
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.Artist
import com.wind.deezerkmp.shared.domain.model.Track
import util.Event

/**
 * Created by Phong Huynh on 10/6/2020
 */
class NavViewModel : ViewModel() {
    val goToTrackDetail: MutableLiveData<Event<Track>> by lazy {
        MutableLiveData<Event<Track>>()
    }
    val goToAlbumDetail: MutableLiveData<Event<Album>> by lazy {
        MutableLiveData<Event<Album>>()
    }
    val goToArtistDetail:  MutableLiveData<Event<Artist>> by lazy {
        MutableLiveData<Event<Artist>>()
    }
    val goToArtistListByGenre: MutableLiveData<Event<GenreTitleModel>> by lazy {
        MutableLiveData<Event<GenreTitleModel>>()
    }
}