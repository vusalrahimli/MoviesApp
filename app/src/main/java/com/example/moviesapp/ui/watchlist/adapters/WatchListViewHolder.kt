package com.example.moviesapp.ui.watchlist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.databinding.ItemRowWatchListBinding

class WatchListViewHolder(private val binding: ItemRowWatchListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        model: WatchListModel,
        onItemClick: ((WatchListModel) -> Unit)?,
        onUnSaveClick: ((WatchListModel) -> Unit)?,
    ) {
        binding.model = model
        Log.e("kjfdsn", "$model")

        binding.root.setOnClickListener {
            onItemClick?.invoke(model)
        }

        binding.unSave.setOnClickListener {
            onUnSaveClick?.invoke(model)
        }
    }

    companion object {
        fun from(parent: ViewGroup) =
            WatchListViewHolder(
                ItemRowWatchListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
