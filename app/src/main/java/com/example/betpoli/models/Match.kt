package com.example.betpoli.models

import com.google.gson.annotations.SerializedName

enum class MatchState {
    NOT_STARTED,
    POSTPONED,
    FIRST_HALF,
    HALF_TIME,
    SECOND_HALF,
    EXTRA_TIME,
    PENALTY_SHOOTOUT,
    MATCH_OVER,
    SUSPENDED,
}

data class Match(
    @SerializedName("local")
    val local: String,
    @SerializedName("visitante")
    val visitante: String,
    @SerializedName("gol_local")
    var golesLocal: Int,
    @SerializedName("gol_visitante")
    var golesVisitante: Int,
    @SerializedName("estado")
    val matchState: String,
    @SerializedName("fecha")
    val date: String,
    @SerializedName("_id")
    val id: String
)