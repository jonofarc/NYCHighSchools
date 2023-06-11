package com.example.nychighschools


import android.graphics.drawable.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


class Composables {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomizableTopBar(
        title: String,
        icon: ImageVector? = null,
        onBackClicked: () -> Unit
    ) {
        TopAppBar(
            title = {
                Row(Modifier.padding(start = 8.dp)) {
                    icon?.let {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Back",
                            modifier = Modifier
                                .clickable { onBackClicked() }
                                .padding(end = 8.dp)
                        )
                    }

                    Text(text = title, style = MaterialTheme.typography.titleMedium)
                }
            }
        )
    }


}