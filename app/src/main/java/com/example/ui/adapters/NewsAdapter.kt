package com.example.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.DateUtils
import com.example.R
import com.example.databinding.NewsItemBinding
import com.example.model.news.bo.NewBo


class NewsAdapter(private val listener: NewsListListener) :
    RecyclerView.Adapter<NewsAdapter.NewViewHolder>(){

    private val items: MutableList<NewBo> = mutableListOf()

    fun updateData(newData: List<NewBo>) {
        val diffCallback = NewsListDiffCallback(items, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onNewsSelected(item) }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        return NewViewHolder(
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    class NewViewHolder(private val binding: NewsItemBinding, private val listener: NewsListListener) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewBo) {
            setupFav(item)
            binding.title.text = item.title
            binding.section.text = item.section
            binding.author.text = item.author
            binding.date.text = DateUtils.getNewsDateStringFormatted(item.publishedDate)
            Glide.with(binding.root)
                .load(item.image)
                .centerCrop()
                .into(binding.image)
            binding.favIcon.setOnClickListener {
                listener.onFavClicked(item)
                item.isFavourite = !item.isFavourite
                setupFav(item)
            }
        }

        private fun setupFav(item: NewBo) {
            binding.favIcon.setImageDrawable(ContextCompat.getDrawable(
                binding.root.context,
                if (item.isFavourite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
            ))
        }
    }

    class NewsListDiffCallback(private val oldList: List<NewBo>, private val newList: List<NewBo>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}

interface NewsListListener {
    fun onNewsSelected(news: NewBo)
    fun onFavClicked(news: NewBo)
}
