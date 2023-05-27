package com.dicoding.picodiploma.storyapptest1.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import com.dicoding.picodiploma.storyapptest1.adapter.StoryAdapter
import com.dicoding.picodiploma.storyapptest1.network.model.Story
import com.dicoding.picodiploma.storyapptest1.ui.CoroutinesRule
import com.dicoding.picodiploma.storyapptest1.ui.DataDummy
import com.dicoding.picodiploma.storyapptest1.ui.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesRule = CoroutinesRule()

    private val dummyNews = DataDummy.generateDummyListStory()

    @Mock
    lateinit var viewModel: MainViewModel

    @Test
    fun `when List Story Should Not Null`() =  coroutinesRule.runBlockingTest {
        val data = PagedTestDataSources.snapshot(dummyNews)
        val stories = MutableLiveData<PagingData<Story>>()
        stories.value = data
        Mockito.`when`(viewModel.getStory("token")).thenReturn(stories)
        val actualStories = viewModel.getStory("token").getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = StoryAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = coroutinesRule.dispatcher,
            workerDispatcher = coroutinesRule.dispatcher,
        )
        differ.submitData(actualStories)
    }
    class PagedTestDataSources private constructor(private val items: List<Story>) :
    PagingSource<Int, LiveData<List<Story>>>() {
        companion object {
            fun snapshot(items: List<Story>): PagingData<Story> {
                return PagingData.from(items)
            }
        }
        override fun getRefreshKey(state: PagingState<Int, LiveData<List<Story>>>): Int {
            return 0
        }
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<Story>>> {
            return LoadResult.Page(emptyList(), 0 , 1)
        }
    }


    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}