package com.dicoding.picodiploma.storyapptest1.ui.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import com.dicoding.picodiploma.storyapptest1.network.response.PostResponse
import com.dicoding.picodiploma.storyapptest1.ui.DataDummy
import com.dicoding.picodiploma.storyapptest1.ui.getOrAwaitValue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var postViewModel: PostViewModel
    private val dummyNews = DataDummy.generateDummyPostResponse()

    @Before
    fun setUp() {
        postViewModel = PostViewModel(storyRepository)
    }

    @Test
    fun `when Post Story Return Success`() {
        val desc = "deskripsi".toRequestBody("text/plain".toMediaType())
        val file = Mockito.mock(File::class.java)
        val requestImageFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            "nameFile",
            requestImageFile
        )

        val expectedMaps = MutableLiveData<Result<PostResponse>>()
        expectedMaps.value = Result.Success(dummyNews)
        Mockito.`when`(
            postViewModel.addPostStory(
                imageMultipart,
                desc, 1.67, 1.85, "token"
            )
        )
            .thenReturn(expectedMaps)

        val actualStory = postViewModel.addPostStory(
            imageMultipart,
            desc, 1.67,
            1.85, "token"
        )
            .getOrAwaitValue()

        Mockito.verify(storyRepository).postStory(
            imageMultipart,
            desc,
            1.67,
            1.85,
            "token"
        )

        assertNotNull(actualStory)
        assertTrue(actualStory is Result.Success)
    }

    @Test
    fun `when Post Story Return Error`() {
        val desc = "deskripsi".toRequestBody("text/plain".toMediaType())
        val file = Mockito.mock(File::class.java)
        val requestImageFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            "nameFile",
            requestImageFile
        )

        val expectedMaps = MutableLiveData<Result<PostResponse>>()
        expectedMaps.value = Result.Failure("Error")
        Mockito.`when`(
            postViewModel.addPostStory(
                imageMultipart,
                desc,
                1.67,
                1.85, "token"
            )
        )
            .thenReturn(expectedMaps)

        val actualStory = postViewModel.addPostStory(
            imageMultipart,
            desc,
            1.67,
            1.85, "token"
        )
            .getOrAwaitValue()

        Mockito.verify(storyRepository).postStory(
            imageMultipart,
            desc,
            1.67,
            1.85,
            "token"
        )

        assertNotNull(actualStory)
        assertTrue(actualStory is Result.Failure)
    }
}