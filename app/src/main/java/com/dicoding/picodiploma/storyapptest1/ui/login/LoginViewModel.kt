package com.dicoding.picodiploma.storyapptest1.ui.login

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository

class LoginViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getLogin(email: String, password: String) = storyRepository.userLogin(email, password)
}