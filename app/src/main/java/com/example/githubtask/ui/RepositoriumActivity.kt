package com.example.githubtask.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtask.R
import com.example.githubtask.data.CommitViewModelClass
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_repositorium.*
import kotlinx.android.synthetic.main.user_rep_in_list.*

class RepositoriumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositorium)

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
                        Picasso.get().load(result[1].repository.owner.avatar_url)
                            .into(img_view_for_user_photo)

                        textview_title_of_repo.text = result[0].repository.name
                    }

                    val stars = result[0].repository.stargazers_count
                    textview_start_in_repo.text = "Number of Stars ($stars)"

            }
        }

        textview_back_icon.setOnClickListener { startActivity(Intent(this, SearchActivity::class.java)) }
    }
}