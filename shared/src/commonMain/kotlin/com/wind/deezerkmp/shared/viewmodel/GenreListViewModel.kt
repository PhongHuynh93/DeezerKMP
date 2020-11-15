package com.wind.deezerkmp.shared.viewmodel

import com.wind.animelist.shared.base.BaseViewModel
import com.wind.animelist.shared.base.ioDispatcher
import com.wind.deezerkmp.shared.domain.data
import com.wind.deezerkmp.shared.domain.model.Genre
import com.wind.deezerkmp.shared.domain.usecase.GetGenreListParam
import com.wind.deezerkmp.shared.domain.usecase.GetGenreListUseCase
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
class GenreListViewModel : BaseViewModel(), KoinComponent {
    private val getGenreListUseCase: GetGenreListUseCase by inject()

    private val _data = MutableStateFlow<List<Genre>?>(null)
    val data: CFlow<List<Genre>> get() = _data.filterNotNull().asCommonFlow()
    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: CFlow<LoadState> get() = _loadState.filterNotNull().asCommonFlow()

    fun start() {
        clientScope.launch(ioDispatcher) {
            _data.value = getGenreListUseCase(GetGenreListParam).data
            _loadState.value = LoadState.NotLoading.Complete
        }
    }
}