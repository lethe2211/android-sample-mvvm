package com.example.samplemvvm.repository

import com.example.samplemvvm.api.APIResult
import com.example.samplemvvm.api.GithubAPI
import com.example.samplemvvm.domain.entity.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RepoRepositoryImpl(
    private val api: GithubAPI
) : RepoRepository {

    override fun findRepoListByUserName(userName: String): Flow<APIResult<List<Repo>>> {
        return flow {
            emit(APIResult.Loading)
//            println("Calling API")
            val apiResult = try {
//                println("Fetching response")
                val response = api.getRepoList(userName)
//                println("findRepoListByUserName: $response")
                APIResult.Success(response)
            } catch (e: Exception) {
                e.printStackTrace()
                APIResult.Failure(e)
            }
            emit(apiResult)
        }
    }
}