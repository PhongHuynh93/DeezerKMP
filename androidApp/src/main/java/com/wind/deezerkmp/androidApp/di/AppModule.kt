package com.wind.deezerkmp.androidApp.di

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wind.deezerkmp.androidApp.ui.adapter.GenreListAdapter
import com.wind.deezerkmp.shared.viewmodel.GenreListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Phong Huynh on 10/4/2020
 */
@ExperimentalCoroutinesApi
val appModule = module {
    factory { (frag: Fragment) ->
        val applicationContext = frag.requireContext().applicationContext
        GenreListAdapter(applicationContext, Glide.with(frag))
    }
    viewModel { GenreListViewModel() }
}