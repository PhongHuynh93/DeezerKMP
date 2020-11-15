package com.wind.deezerkmp.androidApp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.wind.deezerkmp.androidApp.databinding.ItemGenreBinding
import com.wind.deezerkmp.shared.domain.model.Genre

/**
 * Created by Phong Huynh on 11/15/2020
 */
class GenreListAdapter constructor(
    private val context: Context,
    private val requestManager: RequestManager
) : ListAdapter<Genre, GenreViewHolder>(object : DiffUtil
.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                if (bindingAdapterPosition >= 0) {
                    callback?.onClick(getItem(bindingAdapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.rm = requestManager
        holder.binding.item = getItem(position)
        holder.binding.executePendingBindings()
    }

    fun setData(list: List<Genre>) {
        submitList(list)
    }

    var callback: Callback? = null

    interface Callback {
        fun onClick(item: Genre)
    }
}