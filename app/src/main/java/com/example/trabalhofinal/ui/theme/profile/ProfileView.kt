package com.example.trabalhofinal.ui.themes.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trabalhofinal.ui.theme.TrabalhoFinalTheme
import com.example.trabalhofinal.ui.theme.TrabalhoFinalTheme
import com.example.trabalhofinal.ui.theme.profile.ProfileViewModel

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val viewModel: ProfileViewModel = viewModel()
    val state = viewModel.state.value

    Column(modifier = modifier.fillMaxSize()) {
        // Campo de texto para nome
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("enter name")
            },
            value = state.name ?: "",
            onValueChange = viewModel::onNameChange
        )

        // Exibe o e-mail do usuário
        Text(state.user?.email ?: "")

        // Botão de Salvar
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.saveUser()
                navController.popBackStack()  // Navega de volta
            }
        ) {
            Text("Save")
        }

        // Botão de Logout
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.logout()
                navController.navigate("login")
            }
        ) {
            Text("Logout")
        }

        // Botão de Voltar para a tela anterior
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navController.popBackStack()  // Volta para a tela anterior
            }
        ) {
            Text("Back")
        }
    }

    // Chama a função para obter os dados do usuário
    LaunchedEffect(key1 = true) {
        viewModel.getUser()
    }
}
