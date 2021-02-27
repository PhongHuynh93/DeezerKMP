package com.wind.deezerkmp.shared.viewmodel

import com.wind.animelist.shared.base.BaseViewModel
import com.wind.deezerkmp.shared.base.ioDispatcher
import com.wind.deezerkmp.shared.domain.data
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.Track
import com.wind.deezerkmp.shared.domain.usecase.GetTrackListInAlbumParam
import com.wind.deezerkmp.shared.domain.usecase.GetTrackListInAlbumUseCase
import com.wind.deezerkmp.shared.util.CFlow
import com.wind.deezerkmp.shared.util.LoadState
import com.wind.deezerkmp.shared.util.asCommonFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Phong Huynh on 11/15/2020
 */
@ExperimentalCoroutinesApi
class TrackListInAlbumViewModel : BaseViewModel(), KoinComponent {
    private val getTrackListInAlbumUseCase: GetTrackListInAlbumUseCase by inject()

    private val _data = MutableStateFlow<List<Track>?>(null)
    val data: CFlow<List<Track>> get() = _data.filterNotNull().asCommonFlow()
    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: CFlow<LoadState> get() = _loadState.filterNotNull().asCommonFlow()

    fun start(album: Album) {
        clientScope.launch(ioDispatcher) {
            _data.value = getTrackListInAlbumUseCase(GetTrackListInAlbumParam(album)).data
            _loadState.value = LoadState.NotLoading.Complete
        }
    }
}