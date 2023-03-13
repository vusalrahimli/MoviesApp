package com.example.moviesapp.ui.homepage.nowplaying.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto

class NowPlayingAdapter : ListAdapter<NowPlayingDto, NowPlayingViewHolder>(DifferCallBack) {
    var setOnItemClick: ((NowPlayingDto) -> Unit)? = null
    var setOnSaveClick: ((NowPlayingDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder =
        NowPlayingViewHolder.from(parent)

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
            onSaveClick = setOnSaveClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<NowPlayingDto>() {
        override fun areItemsTheSame(oldItem: NowPlayingDto, newItem: NowPlayingDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NowPlayingDto, newItem: NowPlayingDto) =
            oldItem == newItem
    }
}
