package com.wind.deezerkmp.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.transition.MaterialContainerTransform
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
        vmNav.goToArtistListByGenre.observe(this, EventObserver {
            replaceFragment(R.id.root, ArtistListFragment.newInstance(it.id, it.title))
        })
        vmNav.goToArtistDetail.observe(this, EventObserver {
            val targetFrag = ArtistDetailFragment.newInstance(it.artist, it.view.transitionName, it.view.width, it.view.height, it.imageUrl).apply {
                sharedElementEnterTransition = MaterialContainerTransform()
            }
            supportFragmentManager.commit(true) {
                addSharedElement(it.view, it.view.transitionName)
                setCustomAnimations(
                    0,
                    0,
                    0,
                    com.wind.collagePhotoMaker.share.R.anim.slide_out
                )
                replace(R.id.root, targetFrag)
                addToBackStack(null)
                setReorderingAllowed(true)
            }
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
