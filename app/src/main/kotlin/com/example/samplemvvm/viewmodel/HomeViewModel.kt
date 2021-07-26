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
class HomeViewModel(
        private val repoRepository: RepoRepository
) : ViewModel() {

    private val submitEvent = MutableSharedFlow<Unit>()

    private val _userName = MutableStateFlow("Google")
    val userName: Flow<String> = _userName

    private val apiResult = submitEvent
            .map {
//                println("HomeViewMode#userNamel: ${userName.value}")
                _userName.value
            }
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

    val isLoading = apiResult
            .map {
                it.isLoading
            }

    val isFailure = apiResult
            .map {
//                println("Viewmodel isFailure: ${it.isFailure}")
                it.isFailure
            }

    init {
        viewModelScope.launch {
            submitEvent.emit(Unit)
        }
    }

    fun submit() {
        viewModelScope.launch {
//            println("FormViewModel#submit: ${userName.value}")
            submitEvent.emit(Unit)
        }
    }

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    class Factory(
            private val repoRepository: RepoRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repoRepository) as T
        }
    }
}