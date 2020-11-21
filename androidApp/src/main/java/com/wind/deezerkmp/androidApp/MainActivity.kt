package com.wind.deezerkmp.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wind.deezerkmp.androidApp.databinding.ActivityMainBinding
import com.wind.deezerkmp.androidApp.ui.track.TrackListFragment
import com.wind.deezerkmp.androidApp.ui.artist.ArtistDetailFragment
import com.wind.deezerkmp.androidApp.ui.artist.ArtistListFragment
import com.wind.deezerkmp.androidApp.ui.track.MiniPlayerFragment
import com.wind.deezerkmp.androidApp.util.NavViewModel
import util.*

class MainActivity : AppCompatActivity() {
    private lateinit var miniPlayerFragment: MiniPlayerFragment
    private lateinit var viewBinding: ActivityMainBinding
    private val vmNav by viewModels<NavViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        fullScreen()
        lightStatusBar(resources.getBoolean(R.bool.use_light_system_bars))
        lightNavigationBar(resources.getBoolean(R.bool.use_light_system_bars))
        if (findFragmentById(R.id.root) == null) {
            addFragment(R.id.root, MainFragment.newInstance())
        }
        val miniPlayerFrag = findFragmentById(R.id.miniPlayer)
        if (miniPlayerFrag == null) {
            miniPlayerFragment = MiniPlayerFragment.newInstance()
            addFragment(R.id.miniPlayer, miniPlayerFragment)
        } else {
            miniPlayerFragment = miniPlayerFrag as MiniPlayerFragment
        }
        viewBinding.mainView.doOnApplyWindowInsets { v, windowInsets, _ ->
            v.setMargins(b = windowInsets.systemWindowInsetBottom)
        }
        vmNav.goToArtistListByGenre.observe(this, EventObserver {
            replaceFragment(R.id.root, ArtistListFragment.newInstance(it.id, it.title))
        })
        vmNav.goToArtistDetail.observe(this, EventObserver {
            replaceFragment(R.id.root, ArtistDetailFragment.newInstance(it))
        })
        vmNav.goToAlbumDetail.observe(this, EventObserver {
            replaceFragment(R.id.root, TrackListFragment.newInstance(it))
        })
        vmNav.playTrack.observe(this, EventObserver {
            viewBinding.miniPlayer.show()
            miniPlayerFragment.setTrack(it)
        })
    }
}
