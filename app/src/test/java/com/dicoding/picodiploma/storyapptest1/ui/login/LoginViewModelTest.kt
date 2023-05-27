package com.dicoding.picodiploma.storyapptest1.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.storyapptest1.data.StoryRepository
import com.dicoding.picodiploma.storyapptest1.network.response.LoginResponse
import com.dicoding.picodiploma.storyapptest1.ui.DataDummy
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.ui.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var loginViewModel: LoginViewModel
    private val dummyNews = DataDummy.generateDummyLoginResponse()

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(storyRepository)
    }

    @Test
    fun `when Login Should Not Null and Return Success`() {
        val expectedLogin = MutableLiveData<Result<LoginResponse>>()
        expectedLogin.value = Result.Success(dummyNews)
        `when`(loginViewModel.getLogin("mail@mail.com","password")).thenReturn(expectedLogin)
        val actualLogin = loginViewModel.getLogin("mail@mail.com","password").getOrAwaitValue()

        Mockito.verify(storyRepository).userLogin("mail@mail.com","password")
        assertNotNull(actualLogin)
        assertTrue(actualLogin is Result.Success)
    }

    @Test
    fun `when Login Error Should Return Error`() {
        val expectedLogin = MutableLiveData<Result<LoginResponse>>()
        expectedLogin.value = Result.Failure("Error")
        `when`(loginViewModel.getLogin("mail@mail.com","password")).thenReturn(expectedLogin)
        val actualLogin = loginViewModel.getLogin("mail@mail.com","password").getOrAwaitValue()

        Mockito.verify(storyRepository).userLogin("mail@mail.com","password")
        assertNotNull(actualLogin)
        assertTrue(actualLogin is Result.Failure)
    }
}