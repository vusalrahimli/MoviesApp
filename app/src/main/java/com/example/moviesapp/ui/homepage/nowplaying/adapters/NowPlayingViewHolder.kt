package com.example.moviesapp.ui.homepage.nowplaying.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.databinding.ItemRowNowPlayingBinding

class NowPlayingViewHolder(private val binding: ItemRowNowPlayingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dto: NowPlayingDto,
        onItemClick: ((NowPlayingDto) -> Unit)?,
        onSaveClick: ((NowPlayingDto) -> Unit)?,
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
            NowPlayingViewHolder(
                ItemRowNowPlayingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
    }
}
