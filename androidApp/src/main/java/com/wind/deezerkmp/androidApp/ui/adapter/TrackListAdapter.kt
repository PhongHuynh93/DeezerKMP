package com.wind.deezerkmp.androidApp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.databinding.ItemTrackBinding
import com.wind.deezerkmp.shared.domain.model.Album
import com.wind.deezerkmp.shared.domain.model.Track

/**
 * Created by Phong Huynh on 11/16/2020
 */
class TrackListAdapter constructor(
private val context: Context,
private val requestManager: RequestManager
) : ListAdapter<Track, TrackViewHolder>(object : DiffUtil
.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                if (bindingAdapterPosition >= 0) {
                    callback?.onClick(getItem(bindingAdapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.binding.rm = requestManager
        holder.binding.item = getItem(position)
        holder.binding.executePendingBindings()
    }

    fun setData(list: List<Track>) {
        submitList(list)
    }

    var callback: Callback? = null

    interface Callback {
        fun onClick(item: Track)
    }
}