package com.wind.deezerkmp.androidApp.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.Hold
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentListBinding
import com.wind.deezerkmp.androidApp.ui.adapter.ArtistListAdapter
import com.wind.deezerkmp.androidApp.util.NavViewModel
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.shared.domain.model.Artist
import com.wind.deezerkmp.shared.viewmodel.ArtistListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import util.Event
import util.gone
import util.recyclerview.GridItemDecoration
import util.setUpToolbar
import util.show

/**
 * Created by Phong Huynh on 11/4/2020
 */
private const val EXTRA_ID = "xId"
private const val EXTRA_GENRE_TITLE = "xGenreTitle"
data class ArtistShareViewModel(val view: View, val artist: Artist, val imageUrl: String)
class ArtistListFragment: Fragment() {
    companion object {
        fun newInstance(id: String, genreTitle: String): ArtistListFragment {
            return ArtistListFragment().apply {
                arguments = bundleOf(EXTRA_ID to id, EXTRA_GENRE_TITLE to genreTitle)
            }
        }
    }

    private lateinit var genreTitle: String
    private val artistListAdapter: ArtistListAdapter by inject { parametersOf(this) }

    private lateinit var viewBinding: FragmentListBinding
    private val artistListViewModel by viewModel<ArtistListViewModel>()
    private val navViewModel by activityViewModels<NavViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistListViewModel.start(requireArguments().getString(EXTRA_ID)!!)
        artistListAdapter.callback = object: ArtistListAdapter.Callback {
            override fun onClick(view: View, item: Artist, imageUrl: String) {
                navViewModel.goToArtistDetail.value = Event(ArtistShareViewModel(view, item, imageUrl))
            }
        }
        genreTitle = requireArguments().getString(EXTRA_GENRE_TITLE)!!
        exitTransition = Hold()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        setUpToolbar(viewBinding.toolBar, getString(R.string.genre_artist_title, genreTitle))
        viewBinding.rcv.apply {
            adapter = artistListAdapter
            val spanCount = 2
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(requireContext().spaceNormal, true, spanCount))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            artistListViewModel.data.onEach { list ->
                if (list.isEmpty()) {
                    viewBinding.rcv.gone()
                    viewBinding.progressBar.show()
                } else {
                    viewBinding.rcv.show()
                    viewBinding.progressBar.gone()
                    artistListAdapter.setData(list)
                }
            }.collect()
        }
    }
}