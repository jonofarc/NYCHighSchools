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


const val schoolsCsvFile = "schools.csv"
const val satScoresCsvFile = "sat_scores.csv"

class CsvUtils {
    private lateinit var schools: List<School>
    private lateinit var satScores: List<SatScores>


    fun getSchools(): List<School> {
        return schools
    }


    fun getSatScores(): List<SatScores> {
        return satScores
    }

    suspend fun getData(context: Context) {

        // Delay for 1 seconds to allow loading animation to display as otherwise the transition feels unnatural
        delay(1000)

        /**
         * because the csv data is not one that will change constantly once the csv files are parsed save the results and just use the getters
         * to retrive the information this will avoid multiple unnecesary parses and the need to handle code in a async way
         */
        schools = getSchoolsFromCSV(context)
        satScores = getSatScoresFromCSV(context)

    }

    private fun getSchoolsFromCSV(context: Context): MutableList<School> {


        val schools = mutableListOf<School>()
        val lines = loadCSV(context, schoolsCsvFile);


        for (i in 1 until lines.size) {

            val record = lines[0] + "\n" + lines[i]
            schools.addAll(parseSchoolsCSV(record))
        }

        return schools


    }

    private fun getSatScoresFromCSV(context: Context): List<SatScores> {


        val satScores = mutableListOf<SatScores>()
        val lines = loadCSV(context, satScoresCsvFile);

        for (i in 1 until lines.size) {

            val record = lines[0] + "\n" + lines[i]
            satScores.addAll(parseSatScoresCSV(record))
        }

        return satScores


    }


    private fun loadCSV(context: Context, fileName: String): MutableList<String> {
        try {


            val lines = mutableListOf<String>()

            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.useLines { fileLines ->

                fileLines.forEach { line ->

                    lines.add(line)
                }
            }



            return lines


        } catch (e: IOException) {
            e.printStackTrace()

            return mutableListOf()
        }


    }


    @OptIn(ExperimentalSerializationApi::class)
    fun parseSchoolsCSV(csvInput: String): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }

        return csv.decodeFromString(ListSerializer(School.serializer()), csvInput.trimIndent())

    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parseSatScoresCSV(csvInput: String): List<SatScores> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }

        return csv.decodeFromString(ListSerializer(SatScores.serializer()), csvInput.trimIndent())

    }

}