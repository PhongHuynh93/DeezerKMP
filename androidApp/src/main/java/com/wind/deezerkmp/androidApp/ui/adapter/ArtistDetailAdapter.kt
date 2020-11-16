package com.wind.deezerkmp.androidApp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wind.deezerkmp.androidApp.ui.album.AlbumListFragment
import com.wind.deezerkmp.androidApp.ui.artist.ARTIST_DETAIL_ALBUM_POS
import com.wind.deezerkmp.androidApp.ui.artist.ARTIST_DETAIL_COUNT
import com.wind.deezerkmp.shared.domain.model.Artist

/**
 * Created by Phong Huynh on 11/16/2020
 */
class ArtistDetailAdapter(frag: Fragment, private val artist: Artist) : FragmentStateAdapter(frag) {
    override fun getItemCount() = ARTIST_DETAIL_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ARTIST_DETAIL_ALBUM_POS -> {
                AlbumListFragment.newInstance(artist.id)
            }
            else -> {
                throw IllegalStateException("Not have pos $position")
            }
        }
    }
}