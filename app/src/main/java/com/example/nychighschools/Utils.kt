package com.example.nychighschools


import com.example.nychighschools.models.School
import kotlinx.coroutines.delay


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.nychighschools.models.SatScores
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Utils {

    fun findSatScoresByDbn(satScoresList: List<SatScores>, dbn: String): SatScores? {
        return satScoresList.find { it.dbn == dbn }
    }

    fun openUrlInBrowser(context: Context, url: String) {

        var checkedUrl = ""
        //check the url contains http://
        checkedUrl = if (url.contains("http://") || url.contains("https://")) {
            url
        } else {
            "http://$url"
        }
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(checkedUrl))
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle the exception or display an error message
            Toast.makeText(context, "Failed to open URL", Toast.LENGTH_SHORT).show()
        }
    }

}