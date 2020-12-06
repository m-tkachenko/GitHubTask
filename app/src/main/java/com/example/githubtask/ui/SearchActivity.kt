package com.example.githubtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtask.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val repoAdapter = GroupAdapter<GroupieViewHolder>()

        repoAdapter.add(RepoItemInList())
        repoAdapter.add(RepoItemInList())

        recyclerview_of_reps.adapter = repoAdapter
    }
}

class RepoItemInList : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

    override fun getLayout(): Int {
        return R.layout.user_rep_in_list
    }

}