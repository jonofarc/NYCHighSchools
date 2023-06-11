package com.example.nychighschools

import android.util.Log
import com.example.nychighschools.csv.CsvFile
import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


class Utils {


    suspend fun loadCSV(): List<School> {
        delay(1000)
        return parseCSV()
    }

    //this function should be updated to recive the csv file so it can be reused with multiple files if needed
    @OptIn(ExperimentalSerializationApi::class)
    fun parseCSV(): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }
        val input = CsvFile().getCsv().trimIndent()
        val parsed = csv.decodeFromString(ListSerializer(School.serializer()), input)

        Log.d("jon", parsed.toString())

        return parsed

    }

}