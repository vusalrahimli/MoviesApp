package com.example.moviesapp.ui.watchlist.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.watchlist.WatchListModel

class WatchListAdapter : ListAdapter<WatchListModel, WatchListViewHolder>(DifferCallBack) {

    var setOnItemClick: ((WatchListModel) -> Unit)? = null
    var setOnUnSaveClick: ((WatchListModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder =
        WatchListViewHolder.from(parent)

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
            onUnSaveClick = setOnUnSaveClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<WatchListModel>() {
        override fun areItemsTheSame(oldItem: WatchListModel, newItem: WatchListModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WatchListModel, newItem: WatchListModel) =
            oldItem == newItem
    }
}
