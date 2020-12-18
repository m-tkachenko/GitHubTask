package com.example.githubtask.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call

interface GithubService {
    @GET("search/repositories")
    fun searchRepo(@Query("q") query: String) : Call<RepoResult>

    @GET("/repos/{owner}/{repo}/commits")
    fun searchCommit (@Path("owner") owner: String,
                      @Path("repo") repo: String) : Call<CommitResult>
}
