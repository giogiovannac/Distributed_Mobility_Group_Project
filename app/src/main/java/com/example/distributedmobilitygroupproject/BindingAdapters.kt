package com.example.distributedmobilitygroupproject

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.distributedmobilitygroupproject.network.SpotifyID
import com.example.distributedmobilitygroupproject.overview.ImageGridAdapter
import com.example.distributedmobilitygroupproject.overview.SpotifyApiStatus

/**
 * Student ID: 991555778
 * Distributed Mobility Group Project
 * created by davidromero
 * on 2023-04-19
 **/

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SpotifyID>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("spotifyApiStatus")
fun bindStatus(statusImageView: ImageView, status: SpotifyApiStatus) {
    when (status) {
        SpotifyApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        SpotifyApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        SpotifyApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
