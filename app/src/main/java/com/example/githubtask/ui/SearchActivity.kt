package com.example.githubtask.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.githubtask.R
import com.example.githubtask.data.Repo
import com.example.githubtask.data.RepoViewModelClass
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.user_rep_in_list.view.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val repoAdapter = GroupAdapter<GroupieViewHolder>()
        recyclerview_of_reps.adapter = repoAdapter

        edittext_search_repos.setOnKeyListener { view, keyCode, keyEvent ->
            if ((keyEvent.action == KeyEvent.ACTION_DOWN) ||
                    keyCode == EditorInfo.IME_ACTION_DONE) {

                val request = edittext_search_repos.text.toString()
                val repoViewItems = RepoViewModelClass(request)

                repoViewItems.reposShowsInRow { list: List<RepoItemInList> ->
                    runOnUiThread {
                        repoAdapter.addAll(list)
                    }
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


        repoAdapter.setOnItemClickListener { item, view ->
            val intent = Intent(this, RepositoriumActivity::class.java)
            val repoItem = item as RepoItemInList

            intent.putExtra("REPO_NAME", repoItem.repo.name)
            intent.putExtra("REPO_AUTHOR_LOGIN", repoItem.repo.owner.login)
            intent.putExtra("REPO_AUTHOR_AVATAR", repoItem.repo.owner.avatar_url)
            intent.putExtra("REPO_TITLE", repoItem.repo.name)
            intent.putExtra("REPO_STARS", repoItem.repo.stargazers_count.toString())

            startActivity(intent)
        }
    }
}

class RepoItemInList(var repo: Repo) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_repo_title.text = repo.name
        viewHolder.itemView.textview_repo_stars.text = repo.stargazers_count.toString()
        Picasso.get().load(repo.owner.avatar_url).into(viewHolder.itemView.imageview_avatar)
    }

    override fun getLayout(): Int {
        return R.layout.user_rep_in_list
    }

}
