package com.example.samplemvvm.container

import com.example.samplemvvm.api.GithubAPI
import com.example.samplemvvm.repository.RepoRepository
import com.example.samplemvvm.repository.RepoRepositoryImpl
import kotlinx.serialization.ExperimentalSerializationApi

/**
 * https://developer.android.com/training/dependency-injection/manual
 */
@ExperimentalSerializationApi
class AppContainer() {
    val githubAPI: GithubAPI
        get() = GithubAPI.factory()

    val repoRepository: RepoRepository by lazy {
        RepoRepositoryImpl(githubAPI)
    }
}