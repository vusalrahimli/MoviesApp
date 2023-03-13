package com.example.moviesapp.ui.moviedetail.reviews.adapters

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieReviewsDto

class ReviewsAdapter : ListAdapter<MovieReviewsDto, ReviewsViewHolder>(DifferCallBack) {

    var setOnItemClick: ((MovieReviewsDto) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder =
        ReviewsViewHolder.from(parent)

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            onItemClick = setOnItemClick,
        )
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_animation)
    }

    private object DifferCallBack : DiffUtil.ItemCallback<MovieReviewsDto>() {

        override fun areContentsTheSame(oldItem: MovieReviewsDto, newItem: MovieReviewsDto) =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: MovieReviewsDto, newItem: MovieReviewsDto) =
            oldItem.id == newItem.id
    }
}
