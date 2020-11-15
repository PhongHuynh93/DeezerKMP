package com.wind.deezerkmp.androidApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.wind.deezerkmp.androidApp.databinding.ListBinding
import com.wind.deezerkmp.androidApp.ui.adapter.GenreListAdapter
import com.wind.deezerkmp.androidApp.util.NavViewModel
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.shared.domain.model.Genre
import com.wind.deezerkmp.shared.viewmodel.GenreListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import util.Event
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
    private lateinit var viewBinding: ListBinding
    private val genreListViewModel by viewModel<GenreListViewModel>()
    private val vmNav by activityViewModels<NavViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreListViewModel.start()
        genreListAdapter.callback = object : GenreListAdapter.Callback {
            override fun onClick(item: Genre) {
                vmNav.goToArtistListByGenre.value = Event(item.id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ListBinding.inflate(inflater, container, false).apply {
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