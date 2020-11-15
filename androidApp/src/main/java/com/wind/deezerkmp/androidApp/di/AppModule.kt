package com.wind.deezerkmp.androidApp.di

import com.wind.deezerkmp.shared.viewmodel.GenreListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phong Huynh on 10/4/2020
 */
@ExperimentalCoroutinesApi
val appModule = module {
    viewModel { GenreListViewModel() }
}