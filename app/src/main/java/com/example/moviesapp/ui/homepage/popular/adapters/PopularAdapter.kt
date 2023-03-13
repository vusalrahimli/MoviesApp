package com.example.moviesapp.ui.homepage.popular.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto

class PopularAdapter : ListAdapter<PopularDto, PopularViewHolder>(DifferCallBack) {

    var setOnItemClick: ((PopularDto) -> Unit)? = null
    var setOnSaveClick: ((PopularDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder =
        PopularViewHolder.from(parent)

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemCLick = setOnItemClick,
            onSaveClick = setOnSaveClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<PopularDto>() {
        override fun areItemsTheSame(oldItem: PopularDto, newItem: PopularDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PopularDto, newItem: PopularDto) =
            oldItem == newItem
    }
}
