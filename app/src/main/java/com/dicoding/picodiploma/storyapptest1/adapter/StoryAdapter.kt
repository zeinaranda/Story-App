package com.dicoding.picodiploma.storyapptest1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.storyapptest1.network.model.Story
import com.dicoding.picodiploma.storyapptest1.databinding.ItemListRowBinding
import com.dicoding.picodiploma.storyapptest1.ui.detail.DetailActivity

class StoryAdapter : PagingDataAdapter<Story, StoryAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private var binding: ItemListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story?) {
            with(binding) {
                Glide.with(imgItemPhoto)
                    .load(story?.photoUrl) // URL Avatar
                    .into(imgItemPhoto)
                tvItemName.text = story?.name
                itemView.setOnClickListener {

                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, story)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

