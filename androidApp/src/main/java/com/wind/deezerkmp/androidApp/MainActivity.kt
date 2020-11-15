package com.wind.deezerkmp.androidApp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wind.deezerkmp.androidApp.ui.artist.ArtistListFragment
import com.wind.deezerkmp.androidApp.util.NavViewModel
import util.EventObserver
import util.addFragment
import util.replaceFragment

class MainActivity : AppCompatActivity(R.layout.fragment) {
    private val vmNav by viewModels<NavViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(R.id.root, MainFragment.newInstance())
        vmNav.goToArtistListByGenre.observe(this, EventObserver {
            replaceFragment(R.id.root, ArtistListFragment.newInstance(it))
        })
    }
}
