package com.retoys.zenith.domain.repository

import com.retoys.zenith.domain.model.User

interface UserRepository {
    suspend fun getUser(): User?
    suspend fun saveUser(user: User)
}