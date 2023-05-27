package com.dicoding.picodiploma.storyapptest1.ui.post

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun addPostStory(
        file: MultipartBody.Part,
        description: RequestBody,
        lat: Double,
        lon: Double,
        token: String,
    ) = storyRepository.postStory(file, description, lat, lon, token)
}