package com.example.moviesapp.ui.homepage.toprated.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.databinding.ItemRowTopRatedBinding

class TopRatedViewHolder(private val binding: ItemRowTopRatedBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: TopRatedDto,
        onItemClick: ((TopRatedDto) -> Unit)?,
        onSaveClick: ((TopRatedDto) -> Unit)?,
    ) {
        binding.dto = dto

        binding.root.setOnClickListener {
            onItemClick?.invoke(dto)
        }

        binding.iwSave.setOnClickListener {
            if (dto.isSaved) {
                binding.iwSave.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.iwSave.context,
                        R.drawable.icon_unsave,
                    ),
                )
//                (binding.iwSave.drawable as AnimatedVectorDrawable?)?.start()
            } else {
                binding.iwSave.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.iwSave.context,
                        R.drawable.favorite_icon2,
                    ),
                )
//                (binding.iwSave.drawable as AnimatedVectorDrawable?)?.start()
            }

            onSaveClick?.invoke(dto)
        }
    }

    companion object {
        fun from(parent: ViewGroup) =
            TopRatedViewHolder(
                ItemRowTopRatedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
