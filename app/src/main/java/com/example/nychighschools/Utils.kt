package com.example.nychighschools


import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


import android.content.Context
import android.util.Log
import com.example.nychighschools.models.SatScores
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Utils {


    fun findSatScoresByDbn(satScoresList: List<SatScores>, dbn: String): SatScores? {
        return satScoresList.find { it.dbn == dbn }
    }

}