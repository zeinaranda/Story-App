package com.dicoding.picodiploma.storyapptest1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.dicoding.picodiploma.storyapptest1.network.ApiService
import com.dicoding.picodiploma.storyapptest1.network.model.Story
import com.dicoding.picodiploma.storyapptest1.network.response.AuthResponse
import com.dicoding.picodiploma.storyapptest1.network.response.LoginResponse
import com.dicoding.picodiploma.storyapptest1.network.response.PostResponse
import com.dicoding.picodiploma.storyapptest1.network.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.lang.Exception

class StoryRepository(
    private val storyDatabase: StoryDatabase,
    private val apiService: ApiService
) {
    fun getStory(token: String): LiveData<PagingData<Story>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStory()
            }
        ).liveData
    }

    fun getStoryLocation(location: Int, token: String): LiveData<Result<StoryResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getAllLocation(token, location)
                emit(Result.Success(response))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Failure(e.message.toString()))
            }
        }

    fun postStory(
        file: MultipartBody.Part,
        description: RequestBody,
        lat: Double,
        lon: Double,
        token: String
    ): LiveData<Result<PostResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.addNewStories(token, file, description, lat, lon)
                emit(Result.Success(response))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Failure(e.message.toString()))
            }
        }

    fun userLogin(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.loginUser(email, password)
                emit(Result.Success(response))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Failure(e.message.toString()))
            }
        }

    fun userRegist(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<AuthResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.registerUser(name, email, password)
                emit(Result.Success(response))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Failure(e.message.toString()))
            }
        }
}



