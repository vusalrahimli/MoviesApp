package com.example.moviesapp.ui.homepage.popular.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.databinding.ItemRowPopularBinding

class PopularViewHolder(private val binding: ItemRowPopularBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: PopularDto,
        onItemCLick: ((PopularDto) -> Unit)?,
        onSaveClick: ((PopularDto) -> Unit)?,
    ) {
        binding.dto = dto

        binding.root.setOnClickListener {
            onItemCLick?.invoke(dto)
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
            PopularViewHolder(
                ItemRowPopularBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
