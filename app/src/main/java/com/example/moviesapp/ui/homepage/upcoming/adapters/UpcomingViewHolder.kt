package com.example.moviesapp.ui.homepage.upcoming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.databinding.ItemRowUpcomingBinding

class UpcomingViewHolder(private val binding: ItemRowUpcomingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: UpcomingDto,
        onItemClick: ((UpcomingDto) -> Unit)? = null,
        onSaveClick: ((UpcomingDto) -> Unit)? = null,
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
            UpcomingViewHolder(
                ItemRowUpcomingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
