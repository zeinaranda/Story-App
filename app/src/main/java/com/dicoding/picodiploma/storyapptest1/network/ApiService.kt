package com.dicoding.picodiploma.storyapptest1.network


import com.dicoding.picodiploma.storyapptest1.network.response.AuthResponse
import com.dicoding.picodiploma.storyapptest1.network.response.LoginResponse
import com.dicoding.picodiploma.storyapptest1.network.response.PostResponse
import com.dicoding.picodiploma.storyapptest1.network.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponse

    @GET("stories")
    suspend fun getAllStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("location") location: Int
    ): StoryResponse

    @GET("stories")
    suspend fun getAllLocation(
        @Header("Authorization") token: String,
        @Query("location") location: Int
    ): StoryResponse

    @Multipart
    @POST("stories")
    suspend fun addNewStories(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Double,
        @Part("lon") lon: Double,
    ): PostResponse
}