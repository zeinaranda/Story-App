package com.dicoding.picodiploma.storyapptest1.data

import android.content.Context
import com.dicoding.picodiploma.storyapptest1.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val database = StoryDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return StoryRepository(database, apiService)
    }
}