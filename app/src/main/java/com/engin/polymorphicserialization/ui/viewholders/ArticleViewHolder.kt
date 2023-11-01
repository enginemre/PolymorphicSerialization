package com.engin.polymorphicserialization.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.databinding.ItemArticleBinding

class ArticleViewHolder(private val binding : ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Section.Articles){
        binding.title.text = article.title
        binding.description.text = article.description
    }

}