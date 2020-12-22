package com.example.githubtask.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModelClass(searchRequest: String){

    private var repoList: List<Repo>? = null
    private val repos = GithubBuilder.getClient().searchRepo(query = searchRequest)

    fun reposShowsInRow( callBack: (List<Repo>) -> Unit ) {

        repos.enqueue(object : Callback<RepoResult> {
            override fun onResponse(call: Call<RepoResult>, response: Response<RepoResult>) {
                if (response.code() == 200) {
                    repoList = response.body()?.items
                    repoList?.let { callBack(it) }
                }
            }

            override fun onFailure(call: Call<RepoResult>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }
        })
    }
}