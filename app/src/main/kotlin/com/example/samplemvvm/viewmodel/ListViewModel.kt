package com.example.samplemvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.samplemvvm.api.APIResult
import com.example.samplemvvm.repository.RepoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ListViewModel(
        private val repoRepository: RepoRepository
) : ViewModel() {

    private val submitEvent = MutableSharedFlow<Unit>()

    private val apiResult = submitEvent
            .map { "Google" }
            .flatMapLatest {
                val response = repoRepository.findRepoListByUserName(it)
//                println("apiResult: $response")
                response
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, APIResult.Loading)


    val repoList = apiResult
            .map {
//                println("repoList: ${it.valueOrNull}")
                it.valueOrNull.orEmpty()
            }

    init {
        viewModelScope.launch {
            submitEvent.emit(Unit)
        }
    }

    fun submit() {
        viewModelScope.launch {
            submitEvent.emit(Unit)
        }
    }

    class Factory(
            private val repoRepository: RepoRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListViewModel(repoRepository) as T
        }
    }
}

