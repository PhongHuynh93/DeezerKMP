package com.wind.deezerkmp.androidApp.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wind.deezerkmp.androidApp.databinding.FragmentGenreListBinding
import com.wind.deezerkmp.androidApp.ui.adapter.GenreListAdapter
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.shared.viewmodel.GenreListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import util.gone
import util.recyclerview.GridItemDecoration
import util.show

/**
 * Created by Phong Huynh on 11/15/2020
 */
class GenreListFragment: Fragment() {
    companion object {
        fun newInstance(): GenreListFragment {
            return GenreListFragment()
        }
    }

    private val genreListAdapter: GenreListAdapter by inject { parametersOf(this) }

    private lateinit var viewBinding: FragmentGenreListBinding
    private val genreListViewModel by viewModel<GenreListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreListViewModel.start()
    }

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
        viewBinding.rcv.apply {
            adapter = genreListAdapter
            val spanCount = 2
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(requireContext().spaceNormal, true, spanCount))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            genreListViewModel.data.onEach { list ->
                if (list.isEmpty()) {
                    viewBinding.rcv.gone()
                    viewBinding.progressBar.show()
                } else {
                    viewBinding.rcv.show()
                    viewBinding.progressBar.gone()
                    genreListAdapter.setData(list)
                }
            }.collect()
        }
    }
}