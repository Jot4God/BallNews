package com.example.trabalhofinal.models

// Modelo para representar uma partida de futebol
data class Match(
    val id: String = "",           // ID único para cada partida (pode ser gerado automaticamente)
    val name: String = "",        // Nome da partida
    val date: String = "",        // Data formatada
    val league: String = "",      // Liga da partida
    val timestamp: Long = 0L      // Timestamp para ordenação ou armazenamento
)
