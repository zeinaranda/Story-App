package com.dicoding.picodiploma.storyapptest1.network.response

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)

