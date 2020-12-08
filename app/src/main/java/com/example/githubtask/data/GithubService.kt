package com.example.githubtask.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepo (@Query("q") query: String) : Observable<RepoResult>
}
