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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nychighschools.models.SatScores
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

const val favoritesKey = "favoritesKey"
const val sharedPrefKey = "sharedPrefKey"

class Utils {


    fun saveOrRemoveFavorite(context: Context, dbn: String) {

        val currentFavorites = getFavorites(context).toMutableList()

        if (currentFavorites.contains(dbn)) {
            currentFavorites.remove(dbn)
        } else {
            currentFavorites.add(dbn)
        }


        val newRawFavorites = currentFavorites.joinToString(separator = ",")

        saveStringToSharedPreferences(context, favoritesKey, newRawFavorites)
    }

    fun getFavorites(context: Context): List<String> {

        val favorites = mutableListOf<String>()

        val rawFavorites = getStringFromSharedPreferences(context, favoritesKey) ?: ""
        val favoritesStrings = rawFavorites.split(",")
        favorites.addAll(favoritesStrings)

        return favorites
    }

    // Function to save a string value in SharedPreferences
    private fun saveStringToSharedPreferences(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Function to retrieve a string value from SharedPreferences
    private fun getStringFromSharedPreferences(context: Context, key: String): String? {
        val sharedPreferences = context.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }


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