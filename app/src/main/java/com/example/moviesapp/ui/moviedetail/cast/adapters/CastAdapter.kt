package com.example.moviesapp.ui.moviedetail.cast.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieCastDto

class CastAdapter : ListAdapter<MovieCastDto, CastViewHolder>(DifferCallBack) {

    var setOnItemClick: ((MovieCastDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder.from(parent)

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<MovieCastDto>() {
        override fun areItemsTheSame(oldItem: MovieCastDto, newItem: MovieCastDto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieCastDto, newItem: MovieCastDto) =
            oldItem == newItem
    }
}
