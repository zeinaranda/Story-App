package com.dicoding.picodiploma.storyapptest1.ui.maps

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository

class MapsViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getStoryLocation(location: Int, token: String) =
        storyRepository.getStoryLocation(location, token)
}