package com.wind.deezerkmp.androidApp.di

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wind.deezerkmp.androidApp.ui.adapter.AlbumListAdapter
import com.wind.deezerkmp.androidApp.ui.adapter.ArtistListAdapter
import com.wind.deezerkmp.androidApp.ui.adapter.GenreListAdapter
import com.wind.deezerkmp.androidApp.ui.adapter.TitleHeaderAdapter
import com.wind.deezerkmp.shared.viewmodel.AlbumListViewModel
import com.wind.deezerkmp.shared.viewmodel.ArtistListViewModel
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
    factory { (frag: Fragment) ->
        val applicationContext = frag.requireContext().applicationContext
        ArtistListAdapter(applicationContext, Glide.with(frag))
    }
    factory { (frag: Fragment) ->
        val applicationContext = frag.requireContext().applicationContext
        AlbumListAdapter(applicationContext, Glide.with(frag))
    }
    factory { (_: Fragment) ->
        TitleHeaderAdapter()
    }
    viewModel { GenreListViewModel() }
    viewModel { ArtistListViewModel() }
    viewModel { AlbumListViewModel() }
}