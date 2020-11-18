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
import com.wind.deezerkmp.androidApp.databinding.ListBinding
import com.wind.deezerkmp.androidApp.ui.adapter.AlbumListAdapter
import com.wind.deezerkmp.androidApp.util.NavViewModel
import com.wind.deezerkmp.androidApp.util.spaceNormal
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.viewmodel.AlbumListViewModel
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
 * Created by Phong Huynh on 11/4/2020
 */
private const val EXTRA_ID = "xId"
class AlbumListFragment : Fragment() {
    companion object {
        fun newInstance(id: String): AlbumListFragment {
            return AlbumListFragment().apply {
                arguments = bundleOf(EXTRA_ID to id)
            }
        }
    }

    private lateinit var viewBinding: ListBinding
    private val albumListFragment: AlbumListFragment by inject { parametersOf(this) }
    private val albumListViewModel by viewModel<AlbumListViewModel>()
    private val navViewModel by activityViewModels<NavViewModel>()
    private val albumListAdapter: AlbumListAdapter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumListViewModel.start(requireArguments().getString(EXTRA_ID)!!)
        albumListAdapter.callback = object: AlbumListAdapter.Callback {
            override fun onClick(item: Album) {
                navViewModel.goToAlbumDetail.value = Event(item)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
            adapter = albumListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(requireContext().spaceNormal, true, 1))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            albumListViewModel.data.onEach { list ->
                if (list.isEmpty()) {
                    viewBinding.rcv.gone()
                    viewBinding.progressBar.show()
                } else {
                    viewBinding.rcv.show()
                    viewBinding.progressBar.gone()
                    albumListAdapter.setData(list)
                }
            }.collect()
        }
    }
}