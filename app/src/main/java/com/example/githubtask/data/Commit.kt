package com.example.githubtask.data

data class Commit (
    val url: String,
    val html_url: String,
    val author: Author,
    val committer: Committer,
    val message: String,
    val repository: Repo
)

data class CommitResult(val total_count: Int, val incomplete_results: Boolean, val items: List<Commit>)
