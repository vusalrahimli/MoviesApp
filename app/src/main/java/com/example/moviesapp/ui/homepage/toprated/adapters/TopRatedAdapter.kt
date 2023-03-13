package com.example.moviesapp.ui.homepage.toprated.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto

class TopRatedAdapter : ListAdapter<TopRatedDto, TopRatedViewHolder>(DifferCallBack) {

    var setOnItemClick: ((TopRatedDto) -> Unit)? = null
    var setOnSaveClick: ((TopRatedDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder =
        TopRatedViewHolder.from(parent)

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
            onSaveClick = setOnSaveClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<TopRatedDto>() {
        override fun areItemsTheSame(oldItem: TopRatedDto, newItem: TopRatedDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TopRatedDto, newItem: TopRatedDto) =
            oldItem == newItem
    }
}
