package com.example.githubtask.data

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtask.ui.RepoItemInList
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search.*

class RepoViewModelClass(searchRequest: String){

    private var repoList: List<Repo>? = null
    private val repos = GithubBuilder.getClient().searchRepo(query = searchRequest)

    fun reposShowsInRow() : PublishSubject<List<RepoItemInList>> {

        val subject = PublishSubject.create<List<RepoItemInList>>()

        repos
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(io())
            .subscribe({
                result ->
                    Log.d("Result", "Repo Result: ${result.items[2]}")
                    repoList = result.items
                    subject.onNext(repoList!!.map { RepoItemInList(it) })
            },{
                error ->
                    error.printStackTrace()
            })

        return subject
    }

}