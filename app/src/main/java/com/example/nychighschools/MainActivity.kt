package com.example.nychighschools

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nychighschools.csv.CsvFile
import com.example.nychighschools.models.School
import com.example.nychighschools.ui.theme.NYCHighSchoolsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NYCHighSchoolsTheme {

                NYCHighSchoolsTheme {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


@Composable
fun MyApp(
    modifier: Modifier = Modifier,
) {

    var selectedSchool by rememberSaveable { mutableStateOf(-1) }
    val schools: MutableState<List<School>?> = rememberSaveable { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        schools.value = Utils().loadCSV(CsvFile().getCsv())
        // Use the parsed schools list
        Log.d("jon", "2")
    }



    Surface(modifier) {

        schools.value.let {
            if (it == null) {
                Loading()
            } else {
                if (selectedSchool >= 0) {
                    SchoolDetails(school = it[selectedSchool], onBackClicked = { selectedSchool = -1 }, modifier = Modifier)
                } else {
                    //  SchoolsView(schools)
                    SchoolsView(it) { index ->
                        selectedSchool = index
                    }


                }
            }
        }


    }


}


@Preview()
@Composable
fun MyAppPreview() {
    MyApp()
}

