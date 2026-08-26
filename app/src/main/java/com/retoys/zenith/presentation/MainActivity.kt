package com.retoys.zenith.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.retoys.zenith.presentation.ui.screens.auth.RegistrationScreen
import com.retoys.zenith.presentation.ui.screens.home.HomeScreen
import com.retoys.zenith.presentation.ui.theme.ZenithTheme
import com.retoys.zenith.presentation.viewmodels.general.MainViewModel
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZenithTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

                val mainViewModel: MainViewModel = hiltViewModel()
                val isUserExists by mainViewModel.isUserExists.collectAsState()

                when (isUserExists) {
                    null -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    false -> {
                        RegistrationScreen(
                            onProfileCreated = {
                                mainViewModel.refreshUserStatus()
                            }
                        )
                    }
                    true -> {
                        HomeScreen()
                    }
                }
            }
        }
    }
}

