package com.wind.deezerkmp.androidApp.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wind.deezerkmp.shared.domain.model.Artist
import util.Event

/**
 * Created by Phong Huynh on 10/6/2020
 */
class NavViewModel : ViewModel() {
    val goToArtistDetail:  MutableLiveData<Event<Artist>> by lazy {
        MutableLiveData<Event<Artist>>()
    }
    val goToArtistListByGenre: MutableLiveData<Event<String>> by lazy {
        MutableLiveData<Event<String>>()
    }
}