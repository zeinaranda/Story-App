package com.dicoding.picodiploma.storyapptest1.ui.maps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import com.dicoding.picodiploma.storyapptest1.network.response.StoryResponse
import com.dicoding.picodiploma.storyapptest1.ui.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.ui.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
class MapsViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var mapsViewModel: MapsViewModel
    private val dummyNews = DataDummy.generateDummyStoryResponse()

    @Before
    fun setUp() {
        mapsViewModel = MapsViewModel(storyRepository)
    }
    @Test
    fun `when Maps with Story Return Success`() {
        val expectedMaps = MutableLiveData<Result<StoryResponse>>()
        expectedMaps.value = Result.Success(dummyNews)
        Mockito.`when`(mapsViewModel.getStoryLocation(1, "token")).thenReturn(expectedMaps)

        val actualStory = mapsViewModel.getStoryLocation(1, "token").getOrAwaitValue()

        Mockito.verify(storyRepository).getStoryLocation(1, "token")
        assertNotNull(actualStory)
        assertTrue(actualStory is Result.Success)
    }

    @Test
    fun `when Maps with Story Return Error`() {
        val expectedMaps = MutableLiveData<Result<StoryResponse>>()
        expectedMaps.value = Result.Failure("Error")
        Mockito.`when`(mapsViewModel.getStoryLocation(1, "token")).thenReturn(expectedMaps)

        val actualStory = mapsViewModel.getStoryLocation(1, "token").getOrAwaitValue()

        Mockito.verify(storyRepository).getStoryLocation(1, "token")
        assertNotNull(actualStory)
        assertTrue(actualStory is Result.Failure)
    }
}