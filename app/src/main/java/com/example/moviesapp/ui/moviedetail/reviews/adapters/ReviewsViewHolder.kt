package com.example.moviesapp.ui.moviedetail.reviews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieReviewsDto
import com.example.moviesapp.databinding.ItemRowReviewsBinding

class ReviewsViewHolder(private val binding: ItemRowReviewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: MovieReviewsDto,
        onItemClick: ((MovieReviewsDto) -> Unit)?,
    ) {
        binding.dto = dto

        binding.root.setOnClickListener {
            onItemClick?.invoke(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup) =
            ReviewsViewHolder(
                ItemRowReviewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
