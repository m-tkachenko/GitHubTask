package com.example.githubtask.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitViewModelClass(repoLogin : String?, repoName : String?) {

    private var commitList: List<CommitListInfo>? = null
    private val commit = GithubBuilder.getClient().searchCommit("$repoLogin", "$repoName")

    fun commitsRequest( callBack: (List<CommitListInfo>) -> Unit ) {

        commit.enqueue(object: Callback<List<CommitListInfo>> {
            override fun onResponse(call: Call<List<CommitListInfo>>, response: Response<List<CommitListInfo>>) {
                if (response.code() == 200) {
                    commitList = response.body()
                    commitList?.let { callBack(it) }
                }
            }

            override fun onFailure(call: Call<List<CommitListInfo>>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }
        })
    }
}
