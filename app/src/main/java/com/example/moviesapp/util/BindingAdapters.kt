package com.example.moviesapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.data.other.Constants.IMAGE_URL
import com.example.moviesapp.util.Util.downloadImage
import com.example.moviesapp.util.Util.dpToPx
import com.willy.ratingbar.ScaleRatingBar

@BindingAdapter("app:setImage")
fun ImageView.setImage(url: String?) {
    if (url.isNullOrEmpty().not()) {
        if (url!!.startsWith("/https:", true)) {
            downloadImage(this, url.removePrefix("/"))
        } else {
            downloadImage(this, IMAGE_URL + url)
        }
    } else {
        setImageResource(R.drawable.no_photo)
    }
}

@BindingAdapter("app:equalSpacing")
fun RecyclerView.itemDecoration(dp: Int) {
    var orientation = 0
    if (layoutManager is GridLayoutManager) {
        orientation = -1
    } else if (layoutManager is LinearLayoutManager) {
        orientation = (layoutManager as LinearLayoutManager).orientation
    }
    if (0 == itemDecorationCount) {
        addItemDecoration(EqualSpacingItemDecoration(dpToPx(dp), orientation, true))
    }
}

@BindingAdapter("app:ratingForRatingBar")
fun ScaleRatingBar.bindMovieRatingForRB(ratingValue: Float) {
    rating = ratingValue
}

@BindingAdapter("app:checkFavorite")
fun ImageView.setImage(isSaved: Boolean) {
    when (isSaved) {
        true -> setImageResource(R.drawable.icon_unsave)
        false -> setImageResource(R.drawable.favorite_icon2)
    }
}

@BindingAdapter("app:setAvatar")
fun ImageView.setAvatar(url: String?) {
    if (url.isNullOrEmpty().not()) {
        if (url!!.startsWith("/https:", true)) {
            downloadImage(this, url.removePrefix("/"))
        } else {
            downloadImage(this, IMAGE_URL + url)
        }
    } else {
        setImageResource(R.drawable.icon_person)
    }
}
