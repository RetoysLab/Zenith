package com.retoys.zenith.presentation.viewmodels.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retoys.zenith.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isUserExists = MutableStateFlow<Boolean?>(null)
    val isUserExists: StateFlow<Boolean?> = _isUserExists

    init {
        checkUser()
    }

    private fun checkUser() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            _isUserExists.value = user != null
        }
    }

    fun refreshUserStatus() {
        checkUser()
    }
}