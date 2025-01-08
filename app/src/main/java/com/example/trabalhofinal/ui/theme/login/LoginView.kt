package com.example.trabalhofinal.ui.theme.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabalhofinal.R
import com.example.trabalhofinal.ui.theme.TrabalhoFinalTheme

@Composable
fun LoginView(modifier: Modifier = Modifier,
              onLoginSuccess: () -> Unit = {},
              onRegisterSuccess: () -> Unit = {}) {

    val context = LocalContext.current // Adicionando o contexto
    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Adicionando a imagem do logo
            val logo: Painter = painterResource(id = R.drawable.logo)
            Image(painter = logo, contentDescription = "Logo", modifier = Modifier.fillMaxWidth().height(200.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de boas-vindas abaixo do logo
            Text(
                text = "Bem-vindo ao Ball News",
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                placeholder = {
                    Text("email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = {
                    Text("password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onLoginClick {
                        onLoginSuccess()
                    }
                },
                content = {
                    Text("Login")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.onRegisterClick {
                        onRegisterSuccess()
                        Toast.makeText(context, "Registrado com sucesso ⚽", Toast.LENGTH_SHORT).show() // Exibe o toast
                    }
                },
                content = {
                    Text("Registrar")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (state.error != null)
                Text(state.error ?: "")
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    TrabalhoFinalTheme(darkTheme = false) {
        LoginView()
    }
}
