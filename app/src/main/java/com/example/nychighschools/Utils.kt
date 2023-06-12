package com.example.nychighschools


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.nychighschools.models.SatScores

const val likesKey = "likesKey"
const val sharedPrefKey = "sharedPrefKey"

class Utils {


    /**
     * this is where the list of schools you have liked reside
     * I found this to be a nice solution without having to get into json xml or file creation for storing a list of strings
     * we just save it as a big string separated by "," and when reading it is needed the process is reversed a the big string is splits by ","
     */
    fun saveOrRemoveLike(context: Context, dbn: String) {

        val currentLikes = getLikes(context).toMutableList()

        if (currentLikes.contains(dbn)) {
            currentLikes.remove(dbn)
        } else {
            currentLikes.add(dbn)
        }


        val newRawLikes = currentLikes.joinToString(separator = ",")

        saveStringToSharedPreferences(context, likesKey, newRawLikes)
    }

    fun getLikes(context: Context): List<String> {

        val likes = mutableListOf<String>()

        val rawLikes = getStringFromSharedPreferences(context, likesKey) ?: ""
        val likesStrings = rawLikes.split(",")
        likes.addAll(likesStrings)

        return likes
    }


    /**
     * Both save and get StringToSharedPreferences are only being used once and a warning for the key is happening
     * but I decided to leave them this way for it can be expanded for more shared preferences
     */
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


    /**
     * this function is to simple it might be unnecesary but is done this way oin case this need to be expanded with additional logic
     * it would all be condensed trough this function
     */
    fun findSatScoresByDbn(satScoresList: List<SatScores>, dbn: String): SatScores? {
        return satScoresList.find { it.dbn == dbn }
    }


    /**
     * this could also be handled with an in-app browser but this way allows the user the flexibility of using its default browser and reduce logic on the app side
     */
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