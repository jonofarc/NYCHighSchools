package com.example.nychighschools

import android.util.Log
import com.example.nychighschools.csv.CsvFile
import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


class Utils {


    suspend fun loadCSV(csvInput: String): List<School> {
        delay(1000)
        return parseCSV(csvInput)
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parseCSV(csvInput: String): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }

        val parsed = csv.decodeFromString(ListSerializer(School.serializer()), csvInput.trimIndent())

        Log.d("jon", parsed.toString())

        return parsed

    }

}