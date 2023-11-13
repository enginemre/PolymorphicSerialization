package com.engin.polymorphicserialization.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.engin.polymorphicserialization.data.dto.Section
import com.engin.polymorphicserialization.databinding.ItemArticleBinding
import com.engin.polymorphicserialization.databinding.ItemImageBinding
import com.engin.polymorphicserialization.databinding.ItemInfoBinding
import com.engin.polymorphicserialization.ui.viewholders.ArticleViewHolder
import com.engin.polymorphicserialization.ui.viewholders.ImageViewHolder
import com.engin.polymorphicserialization.ui.viewholders.InfoViewHolder

class MainRecyclerViewAdapter(
    private val sectionList: MutableList<Section> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> ArticleViewHolder(
                ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            2 -> InfoViewHolder(
                ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ImageViewHolder(
                ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount() = sectionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = sectionList[position]) {
            is Section.Articles -> (holder as ArticleViewHolder).bind(item)
            is Section.Image -> (holder as ImageViewHolder).bind(item)
            is Section.Info -> (holder as InfoViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (sectionList[position]) {
            is Section.Articles -> 1
            is Section.Info -> 2
            else -> 3
        }
    }

    fun updateList(list: List<Section>) {
        sectionList.clear()
        sectionList.addAll(list)
        notifyDataSetChanged()
    }

}