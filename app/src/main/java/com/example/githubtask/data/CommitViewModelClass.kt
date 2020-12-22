package com.example.githubtask.data

import android.util.Log
import com.example.githubtask.ui.CommitItemInList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class commitInfo(
    val commitAuthorName: String?,
    val commitUrl: String?,
    val commitList: List<CommitItemInList>?
)

class CommitViewModelClass(repoLogin : String?, repoName : String?) {

    private var commitList: List<CommitListInfo>? = null
    private val commit = GithubBuilder.getClient().searchCommit("$repoLogin", "$repoName")

    fun commitsRequest( callBack: (commitInfo) -> Unit ) {

        commit.enqueue(object: Callback<List<CommitListInfo>> {
            override fun onResponse(call: Call<List<CommitListInfo>>, response: Response<List<CommitListInfo>>) {
                if (response.code() == 200) {
                    commitList = response.body()

                    val commitAuthorName = commitList?.get(0)?.commit?.author?.name
                    val commitUrl = commitList?.get(0)?.commit?.url

                    var commitItem: List<CommitItemInList>? = null

                    commitItem = listOf(CommitItemInList(commitList?.get(0), 1),
                        CommitItemInList(commitList?.get(1), 2),
                        CommitItemInList(commitList?.get(2), 3))


                    commitList?.let { callBack(commitInfo(commitAuthorName, commitUrl, commitItem)) }
                }
            }

            override fun onFailure(call: Call<List<CommitListInfo>>, t: Throwable) {
                Log.d("ERROR", "${t.message}")
            }
        })
    }
}
