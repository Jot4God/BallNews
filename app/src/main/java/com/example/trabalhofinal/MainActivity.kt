package com.example.trabalhofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.trabalhofinal.ui.theme.TrabalhoFinalTheme
import com.example.trabalhofinal.ui.theme.login.LoginView
import com.example.trabalhofinal.ui.theme.MatchView
import com.example.trabalhofinal.ui.themes.profile.ProfileView
import com.example.trabalhofinal.ui.theme.match.SavedView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

const val TAG = "TrabalhoFinal"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoFinalTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    // Define a navegação entre as telas
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // Cria a navegação entre as telas
        NavHost(navController = navController, startDestination = "login", modifier = Modifier.padding(innerPadding)) {
            composable("login") {
                LoginView(
                    onLoginSuccess = {
                        navController.navigate("match")
                    }
                )
            }
            composable("match") {
                MatchView(navController = navController)
            }
            composable("profile") {
                ProfileView(navController = navController)
            }
            // Tela de jogos salvos
            composable("saved") {
                SavedView(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    TrabalhoFinalTheme {
        AppContent()
    }
}
