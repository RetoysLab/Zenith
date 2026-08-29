package com.retoys.zenith.domain.model

data class User(
    // For Internet Sync
    val username: String? = null,
    val email: String? = null,

    val name: String,
    val age: String,
    val weight: String,
    val height: String
)