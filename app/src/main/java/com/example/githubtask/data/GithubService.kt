package com.example.githubtask.data

import android.database.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepo (@Query("q") query: String,
                    @Query("in:name") name: String) : Observable<RepoResult>
}
