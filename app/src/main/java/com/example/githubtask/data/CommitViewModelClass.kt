package com.example.githubtask.data

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.PublishSubject

class CommitViewModelClass(val repoLogin : String?, val repoName : String?) {

    private var commitList: List<Commit>? = null
    private val commit = GithubBuilder.getClient().searchCommit(query = "${repoLogin}/${repoName}")

    fun commitsRequest(): PublishSubject<List<Commit>>? {
        Log.d("Result", "Repo Name in CommitViewModel: $repoName")
        Log.d("Result", "Repo Login in CommitViewModel: $repoLogin")

        val commitSub = PublishSubject.create<List<Commit>>()

        commit
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(io())
            .subscribe({
                result ->
                commitList = result.items
                Log.d("Result", "Commit Result in ViewModel: ${result.items[0]}")

                Log.d("Result", "Repo Name in CommitViewModel in fun val repoName: $repoName")
                Log.d("Result", "Repo Login in CommitViewModel in fun val repoLogin: $repoLogin")

                Log.d("Result", "Repo Name in CommitViewModel in fun: ${commitList!![0].repository.name}")
                Log.d("Result", "Repo Login in CommitViewModel in fun: ${result.items[2].repository.owner.login}")

                commitSub.onNext(commitList)
                       },{
                error ->
                    error.printStackTrace()
            })

        return commitSub
    }
}