package com.example.trabalhofinal.ui.theme.match

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    // Estado para armazenar os jogos salvos
    private val _matches = MutableStateFlow<List<String>>(emptyList())
    val matches: StateFlow<List<String>> get() = _matches

    // Função para carregar os jogos salvos do Firestore
    fun loadMatchesFromFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                firestore.collection("matches")
                    .get()
                    .addOnSuccessListener { result ->
                        val matchList = result.map { document ->
                            document.getString("match") ?: ""
                        }
                        _matches.value = matchList
                    }
                    .addOnFailureListener { exception ->
                        Log.e("SavedViewModel", "Erro ao carregar os jogos", exception)
                    }
            } catch (e: Exception) {
                Log.e("SavedViewModel", "Erro ao carregar jogos do Firebase", e)
            }
        }
    }

    // Função para excluir um jogo do Firestore
    fun deleteMatchFromFirebase(matchName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                firestore.collection("matches")
                    .whereEqualTo("match", matchName)
                    .get()
                    .addOnSuccessListener { result ->
                        // Verifica se o jogo existe e exclui
                        for (document in result) {
                            firestore.collection("matches").document(document.id).delete()
                                .addOnSuccessListener {
                                    // Atualiza a lista de jogos removendo o item deletado
                                    _matches.value = _matches.value.filter { it != matchName }
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("SavedViewModel", "Erro ao excluir jogo", exception)
                                }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("SavedViewModel", "Erro ao procurar o jogo", exception)
                    }
            } catch (e: Exception) {
                Log.e("SavedViewModel", "Erro ao excluir jogo do Firebase", e)
            }
        }
    }
}
