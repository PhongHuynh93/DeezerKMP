package com.wind.deezerkmp.androidApp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wind.deezerkmp.androidApp.databinding.ItemHeaderBinding
import com.wind.deezerkmp.androidApp.ui.TYPE_HEADER

/**
 * Created by Phong Huynh on 10/7/2020
 */
class TitleHeaderAdapter : ListAdapter<String, TitleHeaderAdapter.TitleHeaderViewHolder>(object : DiffUtil
.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return true
    }
}) {

    init {
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    var callback: Callback? = null

    override fun getItemViewType(position: Int): Int {
        return TYPE_HEADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHeaderViewHolder {
        val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleHeaderViewHolder(
            binding
        ).apply {
            itemView.setOnClickListener { view ->
                val pos = bindingAdapterPosition
                if (pos >= 0) {
                    getItem(pos)?.let {
                        callback?.onClick(view, pos, it)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TitleHeaderViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }

    interface Callback {
        fun onClick(view: View, pos: Int, item: String)
    }

    inner class TitleHeaderViewHolder(val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root)
}