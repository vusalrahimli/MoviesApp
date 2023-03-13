package com.example.moviesapp.ui.homepage.upcoming.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto

class UpcomingAdapter : ListAdapter<UpcomingDto, UpcomingViewHolder>(DifferCallBack) {

    var setOnItemClick: ((UpcomingDto) -> Unit)? = null
    var setOnSaveClick: ((UpcomingDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder =
        UpcomingViewHolder.from(parent)

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
            onSaveClick = setOnSaveClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<UpcomingDto>() {
        override fun areItemsTheSame(oldItem: UpcomingDto, newItem: UpcomingDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UpcomingDto, newItem: UpcomingDto) =
            oldItem == newItem
    }
}
