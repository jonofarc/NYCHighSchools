package com.example.nychighschools

import org.junit.Test


import com.example.nychighschools.models.SatScores
import com.example.nychighschools.models.School
import org.junit.Assert.assertEquals


class CsvUtilsTest {


    @Test
    fun parseSatScoresCSV() {
        val csvInput = "DBN,SCHOOL NAME,Num of SAT Test Takers,SAT Critical Reading Avg. Score,SAT Math Avg. Score,SAT Writing Avg. Score\n" +
                "01M292,HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES,29,355,404,363"

        val expectedSatScores = listOf(
            SatScores(
                dbn = "01M292",
                schoolName = "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES",
                numOfSatTestTakers = "29",
                satCriticalReadingAvgScore = "355",
                satMathAvgScore = "404",
                satWritingAvgScore = "363"
            ),
        )

        val parsedSatScores = CsvUtils.parseSatScoresCSV(csvInput)
        assertEquals(expectedSatScores, parsedSatScores)
    }

    @Test
    fun parseSchoolCSV() {
        val csvInput = "dbn,school_name,location,building_code,phone_number,school_email,website\n" +
                "01M292,HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES,\"10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)\",M868,212-524-4360,admissions@theclintonschool.net,www.theclintonschool.net"

        val expectedSchools = listOf(
            School(
                dbn = "01M292",
                schoolName = "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES",
                location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)",
                buildingCode = "M868",
                phoneNumber = "212-524-4360",
                schoolEmail = "admissions@theclintonschool.net",
                website = "www.theclintonschool.net",
            ),
        )

        val parsedSchools = CsvUtils.parseSchoolsCSV(csvInput)
        assertEquals(expectedSchools, parsedSchools)
    }


}