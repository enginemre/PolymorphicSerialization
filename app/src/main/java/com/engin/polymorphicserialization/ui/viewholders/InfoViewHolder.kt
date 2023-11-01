package com.engin.polymorphicserialization.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.databinding.ItemInfoBinding

class InfoViewHolder(private val binding : ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data : Section.Info){
        binding.info.text = data.info
    }
}