package com.example.githubtask.data

data class Repo (
    val id: Int,
    val name: String,
    val full_name: String,
    val owner: Owner,
    val private: Boolean,
    val html_url: String,
    val url: String,
    val stargazers_count: Int
)

data class RepoResult (val total_count: Int, val incomplete_results: Boolean, val items: List<Repo>)
