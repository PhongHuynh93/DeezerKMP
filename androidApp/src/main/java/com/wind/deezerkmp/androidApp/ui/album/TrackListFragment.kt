package com.wind.deezerkmp.androidApp.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wind.deezerkmp.androidApp.R
import com.wind.deezerkmp.androidApp.databinding.FragmentListBinding
import com.wind.deezerkmp.androidApp.databinding.ListBinding
import com.wind.deezerkmp.androidApp.ui.adapter.AlbumListAdapter
import com.wind.deezerkmp.androidApp.ui.adapter.TrackListAdapter
import com.wind.deezerkmp.androidApp.util.NavViewModel
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.Track
import com.wind.deezerkmp.shared.viewmodel.AlbumListViewModel
import com.wind.deezerkmp.shared.viewmodel.TrackListInAlbumViewModel
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
private const val EXTRA_DATA = "xDATA"
class TrackListFragment : Fragment() {
    companion object {
        fun newInstance(album: Album): TrackListFragment {
            return TrackListFragment().apply {
                arguments = bundleOf(EXTRA_DATA to album)
            }
        }
    }

    private lateinit var album: Album
    private lateinit var viewBinding: FragmentListBinding
    private val trackListInAlbumViewModel by viewModel<TrackListInAlbumViewModel>()
    private val navViewModel by activityViewModels<NavViewModel>()
    private val trackListAdapter: TrackListAdapter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        album = requireArguments().getParcelable(EXTRA_DATA)!!
        trackListInAlbumViewModel.start(album.id)
        trackListAdapter.callback = object: TrackListAdapter.Callback {
            override fun onClick(item: Track) {
                navViewModel.goToTrackDetail.value = Event(item)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(viewBinding.toolBar, album.model.name)
        viewBinding.rcv.apply {
            adapter = trackListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(requireContext().spaceNormal, true, 1))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            trackListInAlbumViewModel.data.onEach { list ->
                if (list.isEmpty()) {
                    viewBinding.rcv.gone()
                    viewBinding.progressBar.show()
                } else {
                    viewBinding.rcv.show()
                    viewBinding.progressBar.gone()
                    trackListAdapter.setData(list)
                }
            }.collect()
        }
    }
}