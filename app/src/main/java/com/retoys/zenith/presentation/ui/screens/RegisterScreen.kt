package com.retoys.zenith.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.retoys.zenith.presentation.viewmodels.auth.RegisterViewModel
import com.retoys.zenith.R

@Composable
fun RegistrationScreen(
    onProfileCreated: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event: RegisterViewModel.RegistrationEvent ->
            if (event is RegisterViewModel.RegistrationEvent.ProfileCreated) {
                onProfileCreated()
            }
        }
    }

    RegistrationScreenContent(
        uiState = uiState,
        onUsernameChange = viewModel::updateUsername,
        onEmailChange = viewModel::updateEmail,
        onNameChange = viewModel::updateName,
        onAgeChange = viewModel::updateAge,
        onWeightChange = viewModel::updateWeight,
        onHeightChange = viewModel::updateHeight,
        onCreateProfile = viewModel::createProfile
    )
}

@Composable
fun RegistrationScreenContent(
    uiState: RegisterViewModel.RegistrationUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onCreateProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Создайте профиль", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.username,
            onValueChange = onUsernameChange,
            label = { Text(stringResource(R.string.username_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            label = { Text(stringResource(R.string.firstname_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.age,
            onValueChange = onAgeChange,
            label = { Text(stringResource(R.string.age_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.weight,
            onValueChange = onWeightChange,
            label = { Text(stringResource(R.string.weight_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.height,
            onValueChange = onHeightChange,
            label = { Text(stringResource(R.string.height_label)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (uiState.error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = uiState.error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onCreateProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.create_label))
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RegistrationScreenPreview() {
    val previewState = RegisterViewModel.RegistrationUiState(
        username = "john_doe",
        email = "john@example.com",
        name = "John",
        age = "30",
        weight = "80",
        height = "180"
    )
    MaterialTheme {
        RegistrationScreenContent(
            uiState = previewState,
            onUsernameChange = {},
            onEmailChange = {},
            onNameChange = {},
            onAgeChange = {},
            onWeightChange = {},
            onHeightChange = {},
            onCreateProfile = {}
        )
    }
}