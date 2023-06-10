package com.example.nychighschools.ui

import android.util.Log
import com.example.nychighschools.models.School
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


@Serializable
data class Person(val nickname: String, val name: String?, val appearance: Appearance)

@Serializable
data class Appearance(val gender: Gender?, val age: Int?, val height: Double?)

@Serializable
enum class Gender { MALE, FEMALE }

class Utils {


    suspend fun loadCSV(): List<School> {
        delay(5000)
        return parseCSV()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parseCSV(): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }


        val input = """
        school_name,location,building_code,
"Clinton School Writers & Artists, M.S. 260","10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",M868,
    """.trimIndent()
        val parsed = csv.decodeFromString(ListSerializer(School.serializer()), input)

        return parsed

    }

}