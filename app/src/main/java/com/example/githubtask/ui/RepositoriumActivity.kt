package com.example.githubtask.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtask.R
import com.example.githubtask.data.Commit
import com.example.githubtask.data.CommitListInfo
import com.example.githubtask.data.CommitViewModelClass
import com.example.githubtask.data.commitInfo
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
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
        val repoAuthorAvatar = intent.getStringExtra("REPO_AUTHOR_AVATAR")
        val repoTitle = intent.getStringExtra("REPO_TITLE")
        val repoStars = intent.getStringExtra("REPO_STARS")

        val commit = CommitViewModelClass(repoLogin, repoName)

        commit.commitsRequest {
                commit: commitInfo ->

            textview_repo_author_name.text = commit.commitAuthorName

            commit.commitList?.let { commitAdapter.addAll(it) }

            button_view_online.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(commit.commitUrl))
                startActivity(intent)
            }

            button_share_repo.setOnClickListener {
                val sharingText = "Name of repo: ${repoName}." +
                        "Url adress of this repo: ${commit.commitUrl}"
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, sharingText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

        runOnUiThread {
            Picasso.get().load(repoAuthorAvatar).into(img_view_for_user_photo)
            textview_title_of_repo.text = repoTitle
        }

        textview_start_in_repo.text = "Number of Stars ($repoStars)"

        textview_back_icon.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}

class CommitItemInList(val commitList: CommitListInfo?, val numberOfItem: Int) : Item<GroupieViewHolder>(){
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.textview_commit_author_name.text = commitList?.commit?.committer?.name
            viewHolder.itemView.textview_author_email.text = commitList?.commit?.committer?.email
            viewHolder.itemView.textview_commit_message.text = commitList?.commit?.message
            viewHolder.itemView.textview_number.text = numberOfItem.toString()
        }

        override fun getLayout(): Int {
            return R.layout.commit_info_in_list
        }
    }
