package com.retoys.zenith.domain.usecase

import com.retoys.zenith.domain.model.User
import com.retoys.zenith.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        username: String?,
        email: String?,
        name: String,
        age: String,
        weight: String,
        height: String
    ): User {
        val user = User(
            username = username?.takeIf { it.isNotBlank() },
            email = email?.takeIf { it.isNotBlank() },
            name = name,
            age = age,
            weight = weight,
            height = height
        )
        repository.saveUser(user)
        return user
    }
}