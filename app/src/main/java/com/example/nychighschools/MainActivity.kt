package com.example.nychighschools

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nychighschools.models.School
import com.example.nychighschools.ui.theme.NYCHighSchoolsTheme


//data class School(val name: String, val building: String? = null, val location: String)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent  {
            NYCHighSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    SetUi()


                }
            }
        }
    }
}


@Composable
fun SetUi() {


    //val schools = mutableListOf<School>()
    val schools = remember { mutableStateListOf<School>()}

    //a hacky way to get a big list
    for (i in 1..100) {
        schools.add(School(schoolName = "Clinton School Writers & Artists, M.S. 260", location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)", buildingCode = "M868"))
        schools.add(School(schoolName = "Liberation Diploma Plus High School", location = "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)", buildingCode = "K728"))
        schools.add(School(schoolName = "Women's Academy of Excellence", location = "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)"))

    }

    LazyColumn {
        items(schools) { school ->
            SchoolCard(school, modifier = Modifier.clickable {
                schools.add(School(schoolName = "Women's Academy of Excellence", location = "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)"))
            })
        }
    }

}

@Composable
fun SchoolCard(school: School, modifier: Modifier = Modifier) {

    var isFavorite by remember{mutableStateOf(school.isFavorite)}

    Column {


        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 8.dp, modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.high_school_icon), contentDescription = "School picture", modifier = Modifier
                        // Set image size to 40 dp
                        .size(80.dp)
                        // Clip image to be shaped as a circle
                        .clip(RoundedCornerShape(2.5.dp))
                        .border(1.5.dp, MaterialTheme.colorScheme.primary)
                )

                // Add a horizontal space between the image and the column
                Spacer(modifier = Modifier.width(16.dp))

                // We keep track if the message is expanded or not in this
                // variable
               // var isFavorite by remember { mutableStateOf(false) }



                Column {

                    Text(
                        text = school.schoolName,
                        modifier = modifier,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )

                    Text(
                        text = school.location,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,


                        )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (school.buildingCode.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.building_code, school.buildingCode),
                                modifier = modifier,
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium,

                                )
                        } else {
                            Box(
                                modifier = Modifier

                            )
                        }



                        Image(
                            painter = painterResource(R.drawable.star), colorFilter = ColorFilter.tint(getIsFavoriteColor( isFavorite)),contentDescription = "isFavorite icon", modifier = Modifier
                                .clickable {

                                    toggleIsFavorite(school)
                                    isFavorite = school.isFavorite
                                }
                                .align(Alignment.Top)
                                .size(16.dp)

                        )
                    }


                }
            }


        }


    }


}

fun toggleIsFavorite(school: School){
 school.isFavorite = !school.isFavorite
}

fun getIsFavoriteColor(isFavorite: Boolean): Color {
    return  if (isFavorite) Color.Red else Color.Black
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)

@Composable
fun GreetingPreview() {

    SetUi()
}