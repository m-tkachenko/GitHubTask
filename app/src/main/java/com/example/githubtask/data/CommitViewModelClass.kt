package com.example.githubtask.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitViewModelClass(repoLogin : String?, repoName : String?) {

    private var commitList: List<Commit>? = null
    private val commit = GithubBuilder.getClient().searchCommit("$repoLogin", "$repoName")

    fun commitsRequest(): List<Commit>? {

        commit.enqueue(object: Callback<CommitResult> {
            override fun onResponse(call: Call<CommitResult>, response: Response<CommitResult>) {
                if (response.code() == 200) {

                    commitList?.map { response.body()?.items; commit}
                }
            }

            override fun onFailure(call: Call<CommitResult>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }

        })

        return commitList
    }
}
