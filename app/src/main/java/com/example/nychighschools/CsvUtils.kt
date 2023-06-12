package com.example.nychighschools

import com.example.nychighschools.models.School
import kotlinx.coroutines.delay
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv
import android.content.Context
import com.example.nychighschools.models.SatScores
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


const val schoolsCsvFile = "schools.csv"
const val satScoresCsvFile = "sat_scores.csv"

object CsvUtils {
    private lateinit var schools: List<School>
    private lateinit var satScores: List<SatScores>

    fun getSchools(): List<School> {
        return schools
    }

    fun getSatScores(): List<SatScores> {
        return satScores
    }


    /**
     * as CSV files are not on constant change once a parse is done we store the result on schools and satScores lists that way the data is available in a sync way
     */
    suspend fun getData(context: Context) {
        delay(1000) // Delay for 1 second

        schools = getSchoolsFromCSV(context)
        satScores = getSatScoresFromCSV(context)
    }

    private fun getSchoolsFromCSV(context: Context): MutableList<School> {
        val schools = mutableListOf<School>()
        val lines = loadCSV(context, schoolsCsvFile)

        /**
         * this probably doesnt have to be made line by line but in some instances of low memory parsing all the records at the same time created a stack overflow
         * I think this way is a good way to make sure even large files can be handled
         */
        for (i in 1 until lines.size) {
            val record = lines[0] + "\n" + lines[i]
            schools.addAll(parseSchoolsCSV(record))
        }

        return schools
    }

    private fun getSatScoresFromCSV(context: Context): List<SatScores> {
        val satScores = mutableListOf<SatScores>()
        val lines = loadCSV(context, satScoresCsvFile)


        /**
         * this probably doesnt have to be made line by line but in some instances of low memory parsing all the records at the same time created a stack overflow
         * I think this way is a good way to make sure even large files can be handled
         */
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