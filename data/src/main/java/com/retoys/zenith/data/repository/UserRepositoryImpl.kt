package com.retoys.zenith.data.repository

import com.retoys.zenith.data.local.database.dao.UserDao
import com.retoys.zenith.data.mapper.toDomain
import com.retoys.zenith.data.mapper.toEntity
import com.retoys.zenith.domain.model.User
import com.retoys.zenith.domain.repository.UserRepository

class UserRepositoryImpl (
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUser(): User? {
        return userDao.getUser()?.toDomain()
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
}