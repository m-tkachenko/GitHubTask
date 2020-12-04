package com.example.githubtask.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubBuilder {

    private val client = OkHttpClient.Builder().build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> build(service: Class <T>): T {
        return retrofitBuilder.create(service)
    }
}