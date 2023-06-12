package com.example.nychighschools

import org.junit.Test

import org.junit.Assert.*

import com.example.nychighschools.models.SatScores
import org.junit.Assert.assertEquals


class UtilsTest {

    @Test
    fun findSatScoresByDbn_returnsCorrectSatScores() {
        // Arrange
        val satScoresList = listOf(
            SatScores("DBN1", "School1"),
            SatScores("DBN2", "School2"),
            SatScores("DBN3", "School3")
        )
        val dbn = "DBN2"
        val utils = Utils()

        // Act
        val result = utils.findSatScoresByDbn(satScoresList, dbn)

        // Assert
        assertEquals("School2", result?.schoolName)
    }

    @Test
    fun findSatScoresByDbn_returnsNullIfNotFound() {
        // Arrange
        val satScoresList = listOf(
            SatScores("DBN1", "School1"),
            SatScores("DBN2", "School2"),
            SatScores("DBN3", "School3")
        )
        val dbn = "DBN4"
        val utils = Utils()

        // Act
        val result = utils.findSatScoresByDbn(satScoresList, dbn)

        // Assert
        assertEquals(null, result)
    }
}