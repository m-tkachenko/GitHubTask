package com.example.githubtask.data

data class CommitListInfo (
    val commit : Commit
    )

data class Commit (
    val url: String,
    val html_url: String,
    val author: Author,
    val committer: Committer,
    val message: String
)

data class CommitResult(val items: List<CommitListInfo>)
