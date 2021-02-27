package com.wind.deezerkmp.shared.viewmodel

import com.wind.animelist.shared.base.BaseViewModel
import com.wind.deezerkmp.shared.base.ioDispatcher
import com.wind.deezerkmp.shared.domain.data
import com.wind.deezerkmp.shared.domain.model.Artist
import com.wind.deezerkmp.shared.domain.usecase.GetArtistListByGenreParam
import com.wind.deezerkmp.shared.domain.usecase.GetArtistListByGenreUseCase
import com.wind.deezerkmp.shared.util.CFlow
import com.wind.deezerkmp.shared.util.LoadState
import com.wind.deezerkmp.shared.util.asCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Phong Huynh on 11/15/2020
 */
class ArtistListViewModel : BaseViewModel(), KoinComponent {
    private val getArtistListByGenreUseCase: GetArtistListByGenreUseCase by inject()

    private val _data = MutableStateFlow<List<Artist>?>(null)
    val data: CFlow<List<Artist>> get() = _data.filterNotNull().asCommonFlow()
    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: CFlow<LoadState> get() = _loadState.filterNotNull().asCommonFlow()

    fun start(id: String) {
        clientScope.launch(ioDispatcher) {
            _data.value = getArtistListByGenreUseCase(GetArtistListByGenreParam(id)).data
            _loadState.value = LoadState.NotLoading.Complete
        }
    }
}