package com.retoys.zenith.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    //For Internet Sync
    val username: String?,
    val email: String?,

    val name: String,
    val age: String,
    val weight: String,
    val height: String
)