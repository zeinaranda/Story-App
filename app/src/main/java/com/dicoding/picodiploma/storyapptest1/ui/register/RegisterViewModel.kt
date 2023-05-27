package com.dicoding.picodiploma.storyapptest1.ui.register

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository

class RegisterViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getRegist(name: String, email: String, password: String) =
        storyRepository.userRegist(name, email, password)
}