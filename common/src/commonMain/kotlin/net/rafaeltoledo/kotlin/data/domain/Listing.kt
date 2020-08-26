package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Listing(
    val modhash: String,
    val dist: Int,
    val children: List<Topic>,
    val after: String?,
    val before: String?
)