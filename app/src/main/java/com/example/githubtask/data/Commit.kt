package com.example.githubtask.data

data class Commit (
    val url: String,
    val html_url: String,
    val author: Author,
    val message: String,
)

data class CommitResult(val total_count: Int, val incomplete_results: Boolean, val items: List<Commit>)
