package com.wind.deezerkmp.shared.viewmodel

import com.wind.animelist.shared.base.BaseViewModel
import com.wind.animelist.shared.base.ioDispatcher
import com.wind.deezerkmp.shared.domain.data
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.usecase.GetAlbumListByArtistParam
import com.wind.deezerkmp.shared.domain.usecase.GetAlbumListByArtistUseCase
import com.wind.deezerkmp.shared.util.CFlow
import com.wind.deezerkmp.shared.util.LoadState
import com.wind.deezerkmp.shared.util.asCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Phong Huynh on 11/16/2020
 */
class AlbumListViewModel: BaseViewModel(), KoinComponent {
    private val getAlbumListByArtistUseCase: GetAlbumListByArtistUseCase by inject()

    private val _data = MutableStateFlow<List<Album>?>(null)
    val data: CFlow<List<Album>> get() = _data.filterNotNull().asCommonFlow()
    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: CFlow<LoadState> get() = _loadState.filterNotNull().asCommonFlow()

    fun start(id: String) {
        clientScope.launch(ioDispatcher) {
            _data.value = getAlbumListByArtistUseCase(GetAlbumListByArtistParam(id)).data
            _loadState.value = LoadState.NotLoading.Complete
        }
    }
}