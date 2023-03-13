package com.example.moviesapp.ui.moviedetail.cast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieCastDto
import com.example.moviesapp.databinding.ItemRowCastBinding

class CastViewHolder(private val binding: ItemRowCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: MovieCastDto,
        onItemClick: ((MovieCastDto) -> Unit)?,
    ) {
        binding.dto = dto

        binding.root.setOnClickListener {
            onItemClick?.invoke(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup) =
            CastViewHolder(
                ItemRowCastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
