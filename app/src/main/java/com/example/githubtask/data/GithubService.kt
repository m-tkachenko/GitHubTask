package com.example.githubtask.data

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface GithubService {
    @GET("search/repositories")
    fun searchRepo (@Query("q") query: String,
                    @Query("in:name") name: String)
}
