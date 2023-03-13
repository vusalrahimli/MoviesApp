package com.example.moviesapp.ui.moviedetail.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.databinding.FragmentReviewBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReviewBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy {
        FragmentReviewBottomSheetBinding.inflate(layoutInflater)
    }

    private val args by navArgs<ReviewBottomSheetArgs>()

    private val reviewModel by lazy {
        args.reviewsDto
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dto = reviewModel
    }
}
