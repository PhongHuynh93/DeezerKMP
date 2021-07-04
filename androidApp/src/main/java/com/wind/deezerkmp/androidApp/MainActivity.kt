package com.wind.deezerkmp.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.transition.MaterialContainerTransform
import com.wind.deezerkmp.androidApp.databinding.ActivityMainBinding
import com.wind.deezerkmp.androidApp.ui.artist.ArtistDetailFragment
import com.wind.deezerkmp.androidApp.ui.artist.ArtistListFragment
import com.wind.deezerkmp.androidApp.ui.track.MiniPlayerFragment
import com.wind.deezerkmp.androidApp.ui.track.TrackListFragment
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
        statusBarColor(getColorEx(R.color.transparent))

        if (findFragmentById(R.id.content) == null) {
            addFragment(R.id.content, MainFragment.newInstance())
        }
        val miniPlayerFrag = findFragmentById(R.id.miniPlayer)
        if (miniPlayerFrag == null) {
            miniPlayerFragment = MiniPlayerFragment.newInstance()
            addFragment(R.id.miniPlayer, miniPlayerFragment)
        } else {
            miniPlayerFragment = miniPlayerFrag as MiniPlayerFragment
        }
        vmNav.goToArtistListByGenre.observe(this, EventObserver {
            replaceFragment(R.id.content, ArtistListFragment.newInstance(it.id, it.title))
        })
        vmNav.goToArtistDetail.observe(this, EventObserver {
            val targetFrag = ArtistDetailFragment.newInstance(it.artist, it.view.transitionName, it.view.width, it.view.height, it.imageUrl).apply {
                sharedElementEnterTransition = MaterialContainerTransform()
            }
            supportFragmentManager.commit(true) {
                addSharedElement(it.view, it.view.transitionName)
                replace(R.id.content, targetFrag)
                addToBackStack(null)
            }
        })
        vmNav.goToAlbumDetail.observe(this, EventObserver {
            replaceFragment(R.id.content, TrackListFragment.newInstance(it))
        })
        val heightMiniPlayer = getDimen(R.dimen.height_mini_player)
        vmNav.playTrack.observe(this, EventObserver {
            viewBinding.miniPlayer.show()
            viewBinding.content.setMargins(b = heightMiniPlayer.toInt())
            miniPlayerFragment.setTrack(it)
        })
    }
}
