package com.dicoding.picodiploma.storyapptest1.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import com.dicoding.picodiploma.storyapptest1.network.model.Story


class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getStory(token: String): LiveData<PagingData<Story>> =
        storyRepository.getStory(token).cachedIn(viewModelScope)
}

