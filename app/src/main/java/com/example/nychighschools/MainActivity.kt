package com.example.nychighschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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


    val schools: MutableState<List<School>?> = rememberSaveable { mutableStateOf(null) }


    val context = LocalContext.current

    LaunchedEffect(Unit) {

        CsvUtils.getData(context)

        schools.value = CsvUtils.getSchools()

    }

    Surface(modifier) {

        schools.value.let {
            when {
                it == null -> Loading()
                it.isEmpty() -> NoSchoolsFound()
                else -> SchoolView(it)
            }
        }

    }

}

@Composable
fun SchoolView(schools: List<School>) {

    var selectedSchool: School? by rememberSaveable { mutableStateOf(null) }
    var searchTextState by rememberSaveable { mutableStateOf("") }
    val filteredSchools = rememberSaveable(searchTextState) {
        schools.filter { school ->
            school.schoolName.contains(searchTextState, ignoreCase = true)
        }
    }

    if (selectedSchool != null) {
        SchoolDetails(school = selectedSchool!!, onBackClicked = { selectedSchool = null }, modifier = Modifier)
    } else {
        Column {

            Composables().CustomizableTopBar(
                title = stringResource(R.string.app_title),
                searchApplied = { searchText ->
                    searchTextState = searchText

                }, defaultSearch = searchTextState
            )
            //  SchoolsView(schools)
            SchoolsView(filteredSchools) { school ->
                selectedSchool = school
            }


        }

    }
}


@Preview()
@Composable
fun MyAppPreview() {
    MyApp()
}

