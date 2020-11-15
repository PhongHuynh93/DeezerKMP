package com.wind.deezerkmp.androidApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.wind.deezerkmp.androidApp.databinding.FragmentMainBinding
import com.wind.deezerkmp.androidApp.ui.home.GenreListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import util.addFragment

/**
 * Created by Phong Huynh on 11/4/2020
 */
class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFragment(R.id.root, GenreListFragment.newInstance())
    }
}