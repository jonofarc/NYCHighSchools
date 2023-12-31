package com.example.nychighschools

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ThumbUpAlt
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nychighschools.models.School


@Composable
fun SchoolsView(schools: List<School>, onSchoolSelected: (school: School) -> Unit) {


    LazyColumn {
        items(items = schools) { school ->
            SchoolCard(school, modifier = Modifier.clickable {
                onSchoolSelected(school)
            })
        }
    }

}

@Composable
fun SchoolCard(school: School, modifier: Modifier) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .shadow(elevation = 8.dp)
    ) {
        CardContent(school)
    }


}

@Composable
private fun CardContent(school: School, modifier: Modifier = Modifier) {


    val utils = Utils()
    val context = LocalContext.current
    val likes = utils.getLikes(context)

    //  var liked by rememberSaveable { mutableStateOf((likes.contains(school.dbn))) }
    var liked by remember { mutableStateOf(false) }
    liked = likes.contains(school.dbn)



    Column {


        Row(modifier = Modifier.padding(all = 8.dp)) {

            Icon(
                imageVector = Icons.Filled.School,
                contentDescription = stringResource(R.string.school_favourite),
                tint = Color.Gray,
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(80.dp)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = school.schoolName,
                    modifier = modifier,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary,
                )

                Text(
                    text = school.getDisplayLocation,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,


                    )

                DataRow(stringResource(R.string.phone_number), school.phoneNumber)
                HyperLinkDataRow(stringResource(R.string.website), school.website, Modifier.clickable { Utils().openUrlInBrowser(context, school.website) })

                DataRow(stringResource(R.string.dbn), school.dbn)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = if (liked) Icons.Default.ThumbUpAlt else Icons.Default.ThumbUpOffAlt,
                        contentDescription = stringResource(R.string.school_favourite),
                        tint = if (liked) MaterialTheme.colorScheme.primary else Color.Gray,
                        modifier = Modifier.clickable {
                            liked = !liked
                            utils.saveOrRemoveLike(context = context, dbn = school.dbn)
                        }
                    )

                }


            }
        }


    }

}

@Preview
@Composable
fun SchoolsViewPreview() {

    val schools = mutableListOf<School>()

    schools.add(School(schoolName = "Clinton School Writers & Artists, M.S. 260", location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)", buildingCode = "M868"))
    schools.add(School(schoolName = "Liberation Diploma Plus High School", location = "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)", buildingCode = "K728"))
    schools.add(School(schoolName = "Women's Academy of Excellence", location = "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)", buildingCode = "K777"))

    schools.add(School(schoolName = "Clinton School Writers & Artists, M.S. 260", location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)", buildingCode = "M868"))
    schools.add(School(schoolName = "Liberation Diploma Plus High School", location = "2865 West 19th Street, Brooklyn, NY 11224 (40.576976, -73.985413)", buildingCode = "K728"))
    schools.add(School(schoolName = "Women's Academy of Excellence", location = "456 White Plains Road, Bronx NY 10473 (40.815043, -73.85607)", buildingCode = "K777"))



    SchoolsView(schools) { }
}
