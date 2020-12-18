package com.example.githubtask.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModelClass(searchRequest: String){

    private var repoList: List<Repo>? = null
    private val repos = GithubBuilder.getClient().searchRepo(query = searchRequest)

    fun reposShowsInRow(): List<Repo>? {

        repos.enqueue(object: Callback<RepoResult>{
            override fun onResponse(call: Call<RepoResult>, response: Response<RepoResult>) {
                if (response.code() == 200) {

                    repoList?.map { response.body()?.items; repos }
                }
            }

            override fun onFailure(call: Call<RepoResult>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }
        })

        return repoList
    }

}