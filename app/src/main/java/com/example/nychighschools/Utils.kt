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


class Utils {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBarWithBackArrow(
        title: String,
        onBackClicked: () -> Unit
    ) {
        TopAppBar(
            title = {
                Row(Modifier.padding(start = 8.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable { onBackClicked() }
                            .padding(end = 8.dp)
                    )
                    Text(text = title, style = MaterialTheme.typography.titleMedium)
                }
            }
        )
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