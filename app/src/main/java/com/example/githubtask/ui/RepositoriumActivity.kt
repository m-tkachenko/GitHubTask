package com.example.githubtask.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtask.R
import com.example.githubtask.data.Commit
import com.example.githubtask.data.CommitViewModelClass
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_repositorium.*
import kotlinx.android.synthetic.main.commit_info_in_list.view.*
import kotlinx.android.synthetic.main.user_rep_in_list.*

class RepositoriumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorium)

        val commitAdapter = GroupAdapter<GroupieViewHolder>()
        recyclerview_of_commits.adapter = commitAdapter

        val repoName = intent.getStringExtra("REPO_NAME")
        val repoLogin = intent.getStringExtra("REPO_AUTHOR_LOGIN")

        Log.d("Result", "Repo Name in RepoActivity: $repoName")
        Log.d("Result", "Repo Login in RepoActivity: $repoLogin")

        val commit = CommitViewModelClass(repoLogin, repoName).commitsRequest()

        if (commit != null) {
            commit
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(io())
                .subscribe { result ->
                    Log.d("Result", "Commit Result in View: ${result[0]}")

                    textview_repo_author_name.text = result[0].author.name

                    runOnUiThread {
//                        Picasso.get().load(result[1].repository.owner.avatar_url)
//                            .into(img_view_for_user_photo)
//
//                        textview_title_of_repo.text = result[0].repository.name
//
                        for (i in 1..3) {
                            commitAdapter.add(CommitItemInList(result[i], i))
                        }
                    }
//
//                    val stars = result[0].repository.stargazers_count
//                    textview_start_in_repo.text = "Number of Stars ($stars)"
//
//                    button_view_online.setOnClickListener {
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result[0].repository.html_url))
//                        startActivity(intent)
//                    }
//
//                    button_share_repo.setOnClickListener {
//                        val sharingText = "Name of repo: ${result[0].repository.name}." +
//                                "Url adress of this repo: ${result[0].repository.html_url}"
//                        val sendIntent: Intent = Intent().apply {
//                            action = Intent.ACTION_SEND
//                            putExtra(Intent.EXTRA_TEXT, sharingText)
//                            type = "text/plain"
//                        }
//                        val shareIntent = Intent.createChooser(sendIntent, null)
//                        startActivity(shareIntent)
//                    }
                }
        }

        textview_back_icon.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
    class CommitItemInList(val commit: Commit, val numberOfItem: Int) : Item<GroupieViewHolder>(){
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.textview_commit_author_name.text = commit.committer.name
            viewHolder.itemView.textview_author_email.text = commit.committer.email
            viewHolder.itemView.textview_commit_message.text = commit.message
            viewHolder.itemView.textview_number.text = numberOfItem.toString()
        }

        override fun getLayout(): Int {
            return R.layout.commit_info_in_list
        }

    }
}