package com.wind.deezerkmp.androidApp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.databinding.ItemAlbumBinding
import com.wind.deezerkmp.shared.domain.model.Album

/**
 * Created by Phong Huynh on 11/16/2020
 */
class AlbumListAdapter constructor(
private val context: Context,
private val requestManager: RequestManager
) : ListAdapter<Album, AlbumViewHolder>(object : DiffUtil
.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                if (bindingAdapterPosition >= 0) {
                    callback?.onClick(getItem(bindingAdapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.rm = requestManager
        holder.binding.item = getItem(position)
        holder.binding.executePendingBindings()
    }

    fun setData(list: List<Album>) {
        submitList(list)
    }

    var callback: Callback? = null

    interface Callback {
        fun onClick(item: Album)
    }
}