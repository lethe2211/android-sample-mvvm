package com.example.samplemvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.samplemvvm.domain.valueobject.RepoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel : ViewModel() {

    // TODO: It will be the value from the API
    private val _repoList = MutableStateFlow(RepoList(
        listOf(
            RepoList.Element(
                "Hoge",
                "1"
            ),
            RepoList.Element(
                "Fuga",
                "2"
            ),
            RepoList.Element(
                "Piyo",
                "3"
            )
        )
    ))
    val repoList = _repoList.asStateFlow()

    fun setRepoList(repoList: RepoList) {
        _repoList.value = repoList
    }
}