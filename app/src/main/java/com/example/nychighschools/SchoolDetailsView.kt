package com.example.nychighschools

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nychighschools.models.School


@Composable
fun SchoolDetails(
    school: School,
    modifier: Modifier, onBackClicked: () -> Unit,
) {
    Column() {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .shadow(elevation = 8.dp)
                .clickable { onBackClicked() }
        ) {
            CardContentDetails(school)
        }


    }


}

@Composable
private fun CardContentDetails(school: School, modifier: Modifier = Modifier) {


    val satScores = CsvUtils.getSatScores();
    Log.d("jon", "111111111111111111111111111111111111111111111")
    Log.d("jon", satScores.toString())
    val utils = Utils()
    val satScore = utils.findSatScoresByDbn(satScores, school.dbn)
    Log.d("jon", satScores.toString())

    Log.d("jon", "222222222222222222222222222222222222222")
    Log.d("jon", satScore.toString())



    Column {


        Row(modifier = Modifier.padding(all = 8.dp)) {

            Icon(
                imageVector = Icons.Filled.Search,
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
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )

                Text(
                    text = school.location,
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,


                    )

                if (school.buildingCode.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.building_code, school.buildingCode),
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                }

                satScore?.let { satScore ->
                    Text(
                        text = stringResource(R.string.number_of_sat_test_takers, satScore.numOfSatTestTakers),
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                    Text(
                        text = stringResource(R.string.sat_critical_reading_avg, satScore.satCriticalReadingAvgScore),
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                    Text(
                        text = stringResource(R.string.sat_math_avg, satScore.satMathAvgScore),
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                    Text(
                        text = stringResource(R.string.sat_writing_avg, satScore.satWritingAvgScore),
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                }


            }
        }


    }

}

@Preview()
@Composable
fun SchoolDetailsPreview() {

    val schools = mutableListOf<School>()
    schools.add(School(schoolName = "Clinton School Writers & Artists, M.S. 260", location = "10 East 15th Street, Manhattan NY 10003 (40.736526, -73.992727)", buildingCode = "M868"))
    SchoolDetails(school = schools[0], onBackClicked = { }, modifier = Modifier)
}
