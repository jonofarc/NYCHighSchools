package com.example.nychighschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nychighschools.models.School
import com.example.nychighschools.ui.Utils
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


    val schools = mutableListOf<School>()
    //there is definetly  a better way of saving which school is selected but this is the first approach that comes to mind I will update it if possible
    var selectedSchool by rememberSaveable { mutableStateOf(-1) }

    //A hacky way to get a big list for Dev purposes
    for (i in 1..100) {
        schools.add(School(schoolName = "Clinton School Writers & Artists, M.S. 260", location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)", buildingCode = "M868"))
        schools.add(School(schoolName = "Liberation Diploma Plus High School", location = "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)", buildingCode = "K728"))
        schools.add(School(schoolName = "Women's Academy of Excellence", location = "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)"))

    }

    Utils().loadCSV()
    Surface(modifier) {
        if (selectedSchool >= 0) {
            SchoolDetails(school = schools[selectedSchool], onBackClicked = { selectedSchool = -1 }, modifier = Modifier)
        } else {
            //  SchoolsView(schools)
            SchoolsView(schools, onSchoolSelected = { index ->
                selectedSchool = index
            })


        }
    }


}


@Preview()
@Composable
fun MyAppPreview() {
    MyApp()
}

