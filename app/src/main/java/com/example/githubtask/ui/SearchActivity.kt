package com.example.githubtask.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtask.R
import com.example.githubtask.data.GithubBuilder
import com.example.githubtask.data.Repo
import com.example.githubtask.data.RepoResult
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.user_rep_in_list.view.*

class SearchActivity : AppCompatActivity() {

    var repoList: List<Repo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val repoAdapter = GroupAdapter<GroupieViewHolder>()

        recyclerview_of_reps.adapter = repoAdapter

        val repos = GithubBuilder.getClient().searchRepo(query = "tuk")

        repos
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(io())
            .subscribe({
                result ->
                Log.d("Result", "Result: ${result.items[0].owner.avatar_url} repositories")

                repoList = result.items

                repoList!!.forEach {
                    repoAdapter.add(RepoItemInList(it))
                }
            }, {
                error ->
                error.printStackTrace()
            })
    }
}

class RepoItemInList(var repo: Repo) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_repo_title.text = repo.name
    }

    override fun getLayout(): Int {
        return R.layout.user_rep_in_list
    }

}
