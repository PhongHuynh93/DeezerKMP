package com.wind.deezerkmp.androidApp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.wind.deezerkmp.androidApp.databinding.ItemAlbumBinding
import com.wind.deezerkmp.androidApp.databinding.ItemArtistBinding
import com.wind.deezerkmp.androidApp.databinding.ItemGenreBinding
import com.wind.deezerkmp.androidApp.databinding.ItemTrackBinding

/**
 * Created by Phong Huynh on 11/15/2020
 */
class GenreViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)
class ArtistViewHolder(val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root)
class AlbumViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)
class TrackViewHolder(val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root)