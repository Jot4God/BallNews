package com.example.trabalhofinal.repositories

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MatchRepository {
    private val client = OkHttpClient()
    private val apiKey = "4c3b784d765e402c99a22ba45fd39ef3"
    private val baseUrl = "https://api.football-data.org/v4/matches?dateFrom=2025-01-10&dateTo=2025-01-18"

    fun fetchMatches(): Map<String, List<String>> {
        val request = Request.Builder()
            .url(baseUrl)
            .addHeader("X-Auth-Token", apiKey)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Erro na requisição: ${response.code}")

            val responseBody = response.body?.string() ?: throw Exception("Resposta vazia")
            val json = JSONObject(responseBody)

            // Parseando os dados para obter as partidas agrupadas por liga
            val matches = json.getJSONArray("matches")
            val groupedMatches = mutableMapOf<String, MutableList<String>>()

            for (i in 0 until matches.length()) {
                val match = matches.getJSONObject(i)
                val homeTeam = match.getJSONObject("homeTeam").getString("name")
                val awayTeam = match.getJSONObject("awayTeam").getString("name")
                val dateTime = match.getString("utcDate")
                val league = match.getJSONObject("competition").getString("name")

                val matchString = "$homeTeam vs $awayTeam - $dateTime"

                if (groupedMatches.containsKey(league)) {
                    groupedMatches[league]?.add(matchString)
                } else {
                    groupedMatches[league] = mutableListOf(matchString)
                }
            }

            return groupedMatches
        }
    }
}
