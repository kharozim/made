package com.ozimos.made.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ozimos.core.R
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.core.presentation.util.loadImage
import com.ozimos.made.databinding.ItemMovieBinding

class MovieAdapter(private val items: List<MovieDomain>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: MovieDomain, position: Int) {
            binding.run {
                val imagePoster = root.context.getString(R.string.url_poster, item.image)
                ivMovie.loadImage(imagePoster)
                tvRating.text = item.rating.toString()
                tvName.text = item.name

                fabFav.isVisible = item.isFavorite
                root.setOnClickListener {
                    clickItem?.onClickItem(item, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyViewHolder {
        return MyViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    private var clickItem: OnClickItem? = null
    fun onClick(listener: OnClickItem) {
        clickItem = listener
    }

    interface OnClickItem {
        fun onClickItem(data: Any?, position: Int)
    }
}