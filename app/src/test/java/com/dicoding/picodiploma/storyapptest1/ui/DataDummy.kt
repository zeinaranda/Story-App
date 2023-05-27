package com.dicoding.picodiploma.storyapptest1.ui

import com.dicoding.picodiploma.storyapptest1.network.model.Story
import com.dicoding.picodiploma.storyapptest1.network.model.User
import com.dicoding.picodiploma.storyapptest1.network.response.AuthResponse
import com.dicoding.picodiploma.storyapptest1.network.response.LoginResponse
import com.dicoding.picodiploma.storyapptest1.network.response.PostResponse
import com.dicoding.picodiploma.storyapptest1.network.response.StoryResponse

object DataDummy {
    fun generateDummyLoginResponse(): LoginResponse {
        val user = User(
            name = "name",
            userId = "userid",
            token = "token"
        )
        return LoginResponse(
            false,
            "success",
            user
        )
    }

    fun generateDummyRegisterResponse(): AuthResponse {
        return AuthResponse(
            false,
            "success",
        )
    }

    fun generateDummyStoryResponse(): StoryResponse {
        val items: MutableList<Story> = arrayListOf()
        for (i in 0..100) {
            val story = Story(
                i.toString(),
                "created + $i",
                "name + $i",
                "description + $i",
                i.toDouble(),
                "id + $i",
                i.toDouble()
            )
            items.add(story)
        }
        return StoryResponse(
            false,
            "success",
            items
        )
    }

    fun generateDummyListStory(): List<Story> {
        val items: MutableList<Story> = arrayListOf()
        for (i in 0..100) {
            val story = Story(
                id = "story-Hre9GGcF3grS4IXS",
                name =  "zeze",
                description = "zzzz",
                photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1668754087748__AdsZ_yb.jpg",
                createdAt = "2022-11-18T06:48:07.749Z",
                lat = -2.2170983,
                lon = 113.8967383
            )
            items.add(story)
        }
        return items
    }

    fun generateDummyPostResponse(): PostResponse{
        return PostResponse(
            false,
            "success",
        )
    }
}