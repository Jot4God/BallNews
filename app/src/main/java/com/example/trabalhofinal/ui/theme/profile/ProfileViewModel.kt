package com.example.trabalhofinal.ui.theme.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.trabalhofinal.models.User
import com.example.trabalhofinal.repositories.UserRepository

data class ProfileState(
    val user: User? = null,
    var name: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProfileViewModel : ViewModel() {
    var state = mutableStateOf(ProfileState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun getUser() {
        UserRepository.get { user ->
            state.value = state.value.copy(
                user = user,
                name = user.name
            )
        }
    }

    fun saveUser() {
        var user = state.value.user
        if (user == null) {
            user = User(state.value.name)
        } else {
            user.name = state.value.name
        }

        UserRepository.save(user = user)
    }

    fun logout() {
        // Implementar a lógica de logout
        FirebaseAuth.getInstance().signOut()
        // Você pode adicionar um callback ou um evento para notificar a UI sobre o logout
    }
}