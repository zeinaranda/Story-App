package com.dicoding.picodiploma.storyapptest1.ui.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import com.dicoding.picodiploma.storyapptest1.network.response.AuthResponse
import com.dicoding.picodiploma.storyapptest1.ui.DataDummy
import com.dicoding.picodiploma.storyapptest1.ui.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var registViewModel: RegisterViewModel
    private val dummyNews = DataDummy.generateDummyRegisterResponse()

    @Before
    fun setUp() {
        registViewModel = RegisterViewModel(storyRepository)
    }

    @Test
    fun `when Register Should Not Null and Return Success`() {
        val expectedRegist = MutableLiveData<Result<AuthResponse>>()
        expectedRegist.value = Result.Success(dummyNews)
        Mockito.`when`(registViewModel.getRegist("name","mail@mail.com", "password")).thenReturn(expectedRegist)
        val actualRegist = registViewModel.getRegist("name","mail@mail.com","password").getOrAwaitValue()

        Mockito.verify(storyRepository).userRegist("name","mail@mail.com","password")
        assertNotNull(actualRegist)
        assertTrue(actualRegist is Result.Success)
    }

    @Test
    fun `when Register Error Should Return Error`() {
        val expectedRegist = MutableLiveData<Result<AuthResponse>>()
        expectedRegist.value = Result.Failure("Error")
        Mockito.`when`(registViewModel.getRegist("name","mail@mail.com", "password")).thenReturn(expectedRegist)
        val actualRegist = registViewModel.getRegist("name","mail@mail.com","password").getOrAwaitValue()

        Mockito.verify(storyRepository).userRegist("name","mail@mail.com","password")
        assertNotNull(actualRegist)
        assertTrue(actualRegist is Result.Failure)
    }
}