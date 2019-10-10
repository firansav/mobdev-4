package com.example.tutorial4

import android.databinding.BindingAdapter
import android.widget.ImageView

object DataBindingAdapters {

    @BindingAdapter("android:src")
    fun setImageResoruce(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}
