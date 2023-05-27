package com.dicoding.picodiploma.storyapptest1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.storyapptest1.R
import com.dicoding.picodiploma.storyapptest1.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.storyapptest1.network.model.Story

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var storyList: Story

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDetail()
    }

    private fun getDetail() {
        val story = intent.getParcelableExtra<Story>(EXTRA_DETAIL)
        if (story != null) {
            storyList = story
        }

        binding.apply {
            Glide.with(binding.root)
                .load(storyList.photoUrl)
                .into(imgItemPhoto)

            tvItemName.text = storyList.name
            tvItemDesc.text = storyList.description
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}