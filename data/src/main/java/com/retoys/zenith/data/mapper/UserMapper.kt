package com.retoys.zenith.data.mapper

import com.retoys.zenith.data.local.database.entity.UserEntity
import com.retoys.zenith.domain.model.User

fun UserEntity.toDomain(): User = User(
    // For Internet Sync
    username = username,
    email = email,

    name = name,
    age = age,
    weight = weight,
    height = height
)

fun User.toEntity(): UserEntity = UserEntity(
    // For Room database
    id = 1,

    // For Internet Sync
    username = username,
    email = email,

    name = name,
    age = age,
    weight = weight,
    height = height
)