package com.example.nychighschools.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SatScores(
    @SerialName("DBN")
    val dbn: String = "",
    @SerialName("SCHOOL NAME")
    val schoolName: String = "",
    @SerialName("Num of SAT Test Takers")
    val numOfSatTestTakers: String = "",
    @SerialName("SAT Critical Reading Avg. Score")
    val satCriticalReadingAvgScore: String = "",
    @SerialName("SAT Math Avg. Score")
    val satMathAvgScore: String = "",
    @SerialName("SAT Writing Avg. Score")
    val satWritingAvgScore: String = "",
)