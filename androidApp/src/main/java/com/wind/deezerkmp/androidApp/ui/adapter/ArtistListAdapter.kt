package com.wind.deezerkmp.androidApp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.databinding.ItemArtistBinding
import com.wind.deezerkmp.shared.domain.model.Artist

/**
 * Created by Phong Huynh on 11/15/2020
 */
class ArtistListAdapter constructor(
    private val context: Context,
    private val requestManager: RequestManager
) : ListAdapter<Artist, ArtistViewHolder>(object : DiffUtil
.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            ItemArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            binding.imgv.setOnClickListener {
                if (bindingAdapterPosition >= 0) {
                    callback?.onClick(it, getItem(bindingAdapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.binding.rm = requestManager
        holder.binding.item = getItem(position)
        holder.binding.imgv.transitionName = getItem(position).id
        holder.binding.executePendingBindings()
    }

    fun setData(list: List<Artist>) {
        submitList(list)
    }

    var callback: Callback? = null

    interface Callback {
        fun onClick(view: View, item: Artist)
    }
}