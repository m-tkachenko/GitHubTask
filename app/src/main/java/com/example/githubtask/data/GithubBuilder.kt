package com.example.githubtask.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubBuilder {

    fun getClient() {
        var retrofitGithub = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}