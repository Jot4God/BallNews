package com.example.trabalhofinal.ui.theme.match

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment

@Composable
fun SavedView(
    navController: NavController,
    viewModel: SavedViewModel = viewModel()
) {
    // Obtém a lista de jogos do Firestore
    val matches = viewModel.matches.collectAsState()

    // Carregar jogos quando a tela for exibida
    LaunchedEffect(Unit) {
        viewModel.loadMatchesFromFirebase()
    }

    // Layout da tela
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Título com ícone de "voltar"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,  // Ícone de voltar
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Text(
                text = "Saved Games \uD83D\uDCE5",  // Emoji de caixa de correio
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Lista de jogos usando LazyColumn
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(matches.value.size) { index ->
                val match = matches.value[index]

                // Exibindo o jogo em um Card com uma sombra e borda arredondada
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = CutCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    // Conteúdo do Card com o nome do jogo
                    Row(modifier = Modifier.padding(16.dp)) {
                        // Título do jogo com estilo em negrito
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = match, // Aqui você pode exibir o nome do jogo
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // Aqui você pode adicionar mais informações sobre o jogo, se necessário
                            Text(
                                text = "Description or Date", // Substitua por mais dados, se houver
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        // Botão de deletar
                        IconButton(
                            onClick = {
                                // Chama a função para excluir o jogo
                                viewModel.deleteMatchFromFirebase(match)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Match",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedViewPreview() {
    SavedView(navController = rememberNavController())
}
