package com.example.nychighschools


import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


import android.content.Context
import com.example.nychighschools.models.SatResults
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


const val schoolsCsvFile = "schools.csv"
const val satScoresCsvFile = "sat_scores.csv"

class CsvUtils {
    private lateinit var schools: List<School>
    private lateinit var satScores: List<SatResults>


    fun getSchools(): List<School> {
        return schools
    }


    fun getSatScores(): List<SatResults> {
        return satScores
    }

    suspend fun getData(context: Context) {

        /**
         * because the csv data is not one that will change constantly once the csv files are parsed save the results and just use the getters
         * to retrive the information this will avoid multiple unnecesary parses and the need to handle code in a async way
         */
        schools = getSchoolsFromCSV(context, schoolsCsvFile)

    }

    suspend fun getSchoolsFromCSV(context: Context, fileName: String): MutableList<School> {

        // Delay for 1 seconds to allow loading animation to display as otherwise the transition feels unnatural
        delay(1000)
        val schools = mutableListOf<School>()
        val lines = loadCSV(context, fileName);


        for (i in 1 until lines.size) {

            val record = lines[0] + "\n" + lines[i]
            schools.addAll(parseCSV(record))
        }

        return schools


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
    fun parseCSV(csvInput: String): List<School> {

        val csv = Csv { hasHeaderRecord = true; ignoreUnknownColumns = true }

        return csv.decodeFromString(ListSerializer(School.serializer()), csvInput.trimIndent())

    }

}