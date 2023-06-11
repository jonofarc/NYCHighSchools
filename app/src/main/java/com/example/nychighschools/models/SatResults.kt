package com.example.nychighschools.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SatResults(
    @SerialName("DBN")
    val dbn: String,
    @SerialName("SCHOOL NAME")
    val schoolName: String,
    @SerialName("Num of SAT Test Takers")
    val numOfSatTestTakers: Int,
    @SerialName("SAT Critical Reading Avg. Score")
    val satCriticalReadingAvgScore: Int,
    @SerialName("SAT Math Avg. Score")
    val satMathAvgScore: Int,
    @SerialName("SAT Writing Avg. Score")
    val satWritingAvgScore: Int
)