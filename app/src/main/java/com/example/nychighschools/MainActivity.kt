package com.example.nychighschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    var selectedSchool: School? by rememberSaveable { mutableStateOf(null) }
    val schools: MutableState<List<School>?> = rememberSaveable { mutableStateOf(null) }


    val context = LocalContext.current

    LaunchedEffect(Unit) {

        CsvUtils.getData(context)

        schools.value = CsvUtils.getSchools()

    }


    Surface(modifier) {

        schools.value.let {
            if (it == null) {
                Loading()
            } else {
                if (it.isEmpty()) {
                    NoSchoolsFound()
                } else {


                    var searchTextState by rememberSaveable { mutableStateOf("") }
                    val filteredSchools = rememberSaveable(schools, searchTextState) {
                        it.filter { school ->
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
                                    searchText?.let {
                                        searchTextState = it
                                    }

                                }, defaultSearch = searchTextState
                            )
                            //  SchoolsView(schools)
                            SchoolsView(filteredSchools) { school ->
                                selectedSchool = school
                            }


                        }

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

