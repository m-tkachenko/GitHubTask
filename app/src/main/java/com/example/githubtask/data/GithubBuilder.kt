package com.example.githubtask.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubBuilder {

    fun getClient(): GithubService {
        val retrofitGithub = Retrofit.Builder()
            .client(okhttp3.OkHttpClient())
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitGithub.create(GithubService::class.java)
    }
}