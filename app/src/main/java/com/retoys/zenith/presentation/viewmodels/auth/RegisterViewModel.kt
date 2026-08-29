package com.retoys.zenith.presentation.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retoys.zenith.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    private val _events = MutableSharedFlow<RegistrationEvent>()
    val events = _events.asSharedFlow()

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(username = value)
    }

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun updateName(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun updateAge(value: String) {
        _uiState.value = _uiState.value.copy(age = value)
    }

    fun updateWeight(value: String) {
        _uiState.value = _uiState.value.copy(weight = value)
    }

    fun updateHeight(value: String) {
        _uiState.value = _uiState.value.copy(height = value)
    }

    fun createProfile() {
        viewModelScope.launch {
            val state = _uiState.value
            if (state.name.isBlank() || state.age.isBlank() || state.weight.isBlank() || state.height.isBlank()) {
                _uiState.value = state.copy(error = "Заполните обязательные поля (имя, возраст, вес, рост)")
                return@launch
            }
            try {
                createUserUseCase(
                    username = state.username.takeIf { it.isNotBlank() },
                    email = state.email.takeIf { it.isNotBlank() },
                    name = state.name,
                    age = state.age,
                    weight = state.weight,
                    height = state.height
                )
                _uiState.value = state.copy(error = null)
                _events.emit(RegistrationEvent.ProfileCreated)
            } catch (e: Exception) {
                _uiState.value = state.copy(error = "Ошибка сохранения: ${e.message}")
            }
        }
    }

    sealed class RegistrationEvent {
        object ProfileCreated : RegistrationEvent()
    }

    data class RegistrationUiState(
        val username: String = "",
        val email: String = "",
        val name: String = "",
        val age: String = "",
        val weight: String = "",
        val height: String = "",
        val error: String? = null
    )
}