package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val title: String,
    val subreddit: String
)
