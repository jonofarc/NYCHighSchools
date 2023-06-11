package com.example.nychighschools


import android.util.Log
import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Utils {

    // this function should be separated into one that reads the CSV and one that creates the list of schools etc so it can be reused on multiple CSVs
    fun loadCSV(context: Context, fileName: String): MutableList<School> {
        try {

            val schools = mutableListOf<School>()
            val lines = mutableListOf<String>()

            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.useLines { fileLines ->

                fileLines.forEach { line ->

                    Log.d("jon", line)
                    lines.add(line)
                }
            }


            for (i in 1 until lines.size) {

                val record = lines[0] + "\n" + lines[i]
                schools.addAll(parseCSV(record))
            }

            return schools


        } catch (e: IOException) {
            e.printStackTrace()

            return mutableListOf()
        }


    }


    @OptIn(ExperimentalSerializationApi::class)
    fun parseCSV(csvInput: String): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }

        return csv.decodeFromString(ListSerializer(School.serializer()), csvInput.trimIndent())

    }

}