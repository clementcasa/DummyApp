package com.example.dummyapp.ui.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun ImageView.loadImageFromUrl(@DrawableRes placeholderDrawable: Int, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl?.nullWhenEmpty())
        .networkPolicy(NetworkPolicy.OFFLINE)
        .placeholder(placeholderDrawable)
        .into(this, object : Callback {
            override fun onSuccess() { }
            override fun onError(e: Exception?) {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(placeholderDrawable)
                    .into(this@loadImageFromUrl)
            }
        })
}