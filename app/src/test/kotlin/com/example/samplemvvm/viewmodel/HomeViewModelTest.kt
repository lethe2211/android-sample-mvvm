package com.example.samplemvvm.viewmodel

import com.example.samplemvvm.api.APIResult
import com.example.samplemvvm.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var repoRepository: RepoRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
//    private val testCoroutineDispatcher = newSingleThreadContext("Main")

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        homeViewModel = HomeViewModel(
            repoRepository,
            testCoroutineDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        reset(repoRepository)
    }

    @Test
    fun `test`() = testCoroutineDispatcher.runBlockingTest {
        homeViewModel.setUserName("lethe2211")
        assertThat(homeViewModel.userName.first(), `is`("lethe2211"))
    }

    @Test
    fun `submit`() = testCoroutineDispatcher.runBlockingTest {
        whenever(repoRepository.findRepoListByUserName(anyString()))
            .thenReturn(
                flowOf(APIResult.Loading)
            )
        homeViewModel.submit()
        verify(repoRepository, times(1)).findRepoListByUserName(anyString())
    }
}