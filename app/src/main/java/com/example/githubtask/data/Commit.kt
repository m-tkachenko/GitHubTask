package com.example.githubtask.data

data class CommitListInfo (
    val url: String,
    val sha: String,
    val comments_url: String,
    val commit : Commit
    )

data class Commit (
    val url: String,
    val author: Author,
    val committer: Committer,
    val message: String
    )

data class CommitResult(val items: List<CommitListInfo>)
