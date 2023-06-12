package com.example.nychighschools


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class Composables {
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomizableTopBar(
        title: String,
        icon: ImageVector? = null,
        onBackClicked: (() -> Unit)? = null,
        searchApplied: ((String) -> Unit)? = null,
        defaultSearch: String = "",
    ) {
        var searchText by remember { mutableStateOf(defaultSearch) }


        TopAppBar(
            title = {
                Row(Modifier.padding(start = 8.dp)) {
                    icon?.let {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Back",
                            modifier = Modifier
                                .clickable { onBackClicked?.let { it1 -> it1() } }
                                .padding(end = 8.dp)
                        )
                    }

                    Text(text = title, style = MaterialTheme.typography.bodySmall)
                }
            },

            actions = {
                if (searchApplied != null)
                    Box(Modifier.fillMaxWidth(0.5f)) {
                        // Add search field here
                        TextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                searchApplied?.let { it(searchText) }
                            },
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterStart),
                            placeholder = { Text(text = "Search") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search"
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {

                                }
                            )
                        )
                    }
            }
        )
    }


}


@Preview
@Composable
fun CustomizableTopBarPreview() {

    Composables().CustomizableTopBar(stringResource(R.string.app_title))
}

@Preview
@Composable
fun CustomizableTopBarWithSearchBarPreview() {

    Composables().CustomizableTopBar(stringResource(R.string.app_title), searchApplied = {})
}