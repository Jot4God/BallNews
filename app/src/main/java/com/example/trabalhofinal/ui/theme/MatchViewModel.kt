package com.example.trabalhofinal.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalhofinal.repositories.MatchRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {
    private val repository = MatchRepository()

    private val _matches = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val matches: StateFlow<Map<String, List<String>>> get() = _matches

    // Instância do Firestore
    private val firestore = FirebaseFirestore.getInstance()

    // Função para carregar as partidas
    fun loadMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.fetchMatches()
                _matches.emit(result)
            } catch (e: Exception) {
                e.printStackTrace()
                _matches.emit(emptyMap())
            }
        }
    }

    // Função para adicionar o jogo ao Firebase
    fun addMatchToFirebase(match: String) {
        // Criando um objeto para representar os dados do jogo
        val matchData = hashMapOf(
            "match" to match,
            "timestamp" to System.currentTimeMillis() // Timestamp para ordenar ou filtrar
        )

        // Enviando os dados para a coleção "matches" no Firestore
        firestore.collection("matches")
            .add(matchData)
            .addOnSuccessListener { documentReference ->
                Log.d("MatchViewModel", "Jogo adicionado com ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("MatchViewModel", "Erro ao adicionar o jogo", e)
            }
    }

}