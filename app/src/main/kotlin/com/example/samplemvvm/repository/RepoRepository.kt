package com.example.samplemvvm.repository

import com.example.samplemvvm.api.APIResult
import com.example.samplemvvm.domain.entity.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun findRepoListByUserName(userName: String): Flow<APIResult<List<Repo>>>
}