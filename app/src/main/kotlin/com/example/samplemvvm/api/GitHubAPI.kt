package com.example.samplemvvm.api

import com.example.samplemvvm.domain.entity.Repo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("/users/{user}/repos")
    suspend fun getRepoList(
        @Path("user") user: String?
    ): List<Repo>

    companion object {
        private const val HTTPS_API_GITHUB_URL = "https://api.github.com"

        @ExperimentalSerializationApi
        fun factory(): GithubAPI {
            val contentType = MediaType.get("application/json")
            return Retrofit.Builder()
                    .baseUrl(HTTPS_API_GITHUB_URL)
                    .addConverterFactory(
                        Json {
                            ignoreUnknownKeys = true
                        }
                            .asConverterFactory(contentType)
                    )
                    .build()
                    .create(GithubAPI::class.java)
        }
    }
}