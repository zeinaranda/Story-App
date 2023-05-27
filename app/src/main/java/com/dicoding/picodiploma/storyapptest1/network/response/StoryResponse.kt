package com.dicoding.picodiploma.storyapptest1.network.response

import com.dicoding.picodiploma.storyapptest1.network.model.Story
import com.google.gson.annotations.SerializedName

data class StoryResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<Story>
        )