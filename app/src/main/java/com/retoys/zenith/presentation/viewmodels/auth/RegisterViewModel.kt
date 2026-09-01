package com.retoys.zenith.presentation.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.retoys.zenith.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@KoinViewModel
class RegisterViewModel @Inject constructor(private val createUserUseCase: CreateUserUseCase) : ViewModel() {
    //
}