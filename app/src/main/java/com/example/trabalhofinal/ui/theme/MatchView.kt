package com.example.trabalhofinal.ui.theme

import android.content.Intent
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import java.text.SimpleDateFormat
import java.util.*
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import android.widget.Toast

@Composable
fun MatchView(
    navController: NavController,
    viewModel: MatchViewModel = viewModel()
) {
    val matches = viewModel.matches.collectAsState()
    val expandedStates = remember { mutableStateOf(mutableMapOf<String, Boolean>()) }
    val context = LocalContext.current // Obter o contexto

    Column(modifier = Modifier.fillMaxSize()) {
        // Cabeçalho
        Text(
            "Football Matches ⚽",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),
            modifier = Modifier.padding(16.dp)
        )

        // Lista de jogos
        LazyColumn(modifier = Modifier.weight(1f)) {
            matches.value.forEach { (league, matchList) ->
                val isExpanded = expandedStates.value[league] ?: false

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$league 🏆",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {
                                expandedStates.value = expandedStates.value.toMutableMap().apply {
                                    this[league] = !isExpanded
                                }
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = if (isExpanded) "Minimizar" else "Expandir"
                            )
                        }
                    }
                }

                // Mostrar os jogos
                if (isExpanded) {
                    items(matchList.size) { index ->
                        val match = matchList[index]
                        val dateTime = match.substringAfterLast(" - ")

                        val formattedDate = try {
                            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                            val formatter = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
                            val date = parser.parse(dateTime)
                            formatter.format(date)
                        } catch (e: Exception) {
                            dateTime
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "⚽ ${match.substringBefore(" - ")}",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = "🕒 $formattedDate",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 3.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.addMatchToFirebase(match)
                                    // Exibe a mensagem "Jogo Salvo" com o Toast
                                    Toast.makeText(context, "Game Saved", Toast.LENGTH_SHORT).show()
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Save,
                                    contentDescription = "Guardar Jogo",
                                    tint = Color.Black
                                )
                            }

                            IconButton(
                                onClick = { shareMatch(context, match, formattedDate) },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "Compartilhar Jogo",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }

        BottomNavigation(
            backgroundColor = Color.Gray,
            modifier = Modifier.height(60.dp)
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile",
                        modifier = Modifier.size(32.dp)
                    )
                },
                label = {
                    Text("Profile")
                },
                selected = false,
                onClick = { navController.navigate("profile") }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Saved",
                        modifier = Modifier.size(32.dp)
                    )
                },
                label = {
                    Text("Saved Games")
                },
                selected = false,
                onClick = { navController.navigate("saved") }
            )
        }
    }

    remember(Unit) {
        viewModel.loadMatches()
    }
}

fun shareMatch(context: Context, match: String, formattedDate: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Check this game:\n ${match.substringBefore(" - ")}\n Data e Hora: $formattedDate")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Compartilhar Jogo"))
}

@Preview(showBackground = true)
@Composable
fun MatchViewPreview() {
    MatchView(navController = rememberNavController())
}