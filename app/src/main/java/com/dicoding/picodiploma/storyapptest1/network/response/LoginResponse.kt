package com.dicoding.picodiploma.storyapptest1.network.response

import com.dicoding.picodiploma.storyapptest1.network.model.User

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val loginResult: User
)
