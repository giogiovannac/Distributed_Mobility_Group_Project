package com.example.distributedmobilitygroupproject.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.distributedmobilitygroupproject.network.SpotifyID
import com.example.distributedmobilitygroupproject.network.SpotifyImage

/**
 * Student ID: 991555778
 * Distributed Mobility Group Project
 * created by davidromero
 * on 2023-04-19
 **/
class ImageGridAdapter {
    class PhotoGridAdapter :
        ListAdapter<SpotifyImage,ImageGridAdapter.SpotifyImageViewHolder>(DiffCallback) {

        /**
         * The MarsPhotosViewHolder constructor takes the binding variable from the associated
         * GridViewItem, which nicely gives it access to the full [MarsPhoto] information.
         */
        class SpotifyImageViewHolder(
            private var binding: GridViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(spotifyImage: SpotifyImage) {
                binding.image = spotifyImage
                // This is important, because it forces the data binding to execute immediately,
                // which allows the RecyclerView to make the correct view size measurements
                binding.executePendingBindings()
            }
        }

        /**
         * Allows the RecyclerView to determine which items have changed when the [List] of
         * [MarsPhoto] has been updated.
         */
        companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
            override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
                return oldItem.imgSrcUrl == newItem.imgSrcUrl
            }
        }

        /**
         * Create new [RecyclerView] item views (invoked by the layout manager)
         */
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MarsPhotosViewHolder {
            return MarsPhotosViewHolder(
                GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
            )
        }

        /**
         * Replaces the contents of a view (invoked by the layout manager)
         */
        override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
            val marsPhoto = getItem(position)
            holder.bind(marsPhoto)
        }
    }
}