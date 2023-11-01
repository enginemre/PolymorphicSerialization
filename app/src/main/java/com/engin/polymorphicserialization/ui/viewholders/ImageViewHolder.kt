package com.engin.polymorphicserialization.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.databinding.ItemImageBinding

class ImageViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Section.Image) {
        Glide.with(binding.image.context).load(data.imageUrl).into(binding.image)
    }
}