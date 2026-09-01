package com.retoys.zenith.presentation.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.retoys.zenith.domain.usecase.CreateUserUseCase
import org.koin.androidx.viewmodel.ext.android.viewModel

@KoinViewModel
class RegisterViewModel (
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    //
}