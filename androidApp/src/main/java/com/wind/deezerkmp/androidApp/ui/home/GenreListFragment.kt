package com.wind.deezerkmp.androidApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wind.deezerkmp.androidApp.databinding.FragmentGenreListBinding
import com.wind.deezerkmp.shared.viewmodel.GenreListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Phong Huynh on 11/15/2020
 */
@ExperimentalCoroutinesApi
class GenreListFragment: Fragment() {
    private lateinit var viewBinding: FragmentGenreListBinding
    private val genreListViewModel by viewModel<GenreListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGenreListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            genreListViewModel.data.onEach { list ->
//                if (list.isEmpty()) {
//                    viewBinding.rcv.gone()
//                    viewBinding.progressBar.show()
//                } else {
//                    viewBinding.rcv.show()
//                    viewBinding.progressBar.gone()
//                    homeMangaAdapter.setData(list)
//                }
            }.collect()
        }
    }
}