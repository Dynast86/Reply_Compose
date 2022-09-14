package com.dynast.replycompose.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.dynast.replycompose.R
import com.dynast.replycompose.temp.search.SearchSuggestion
import com.dynast.replycompose.ui.components.TopBarWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Column {
                TopBarWidget {
                    println("Click : $it")
                }
                Divider(thickness = Dp.Hairline)
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {
            item {
                SuggestWidget(title = R.string.search_suggestion_title_yesterday, suggest = yesterdaySuggestions)
            }
            item {
                SuggestWidget(title = R.string.search_suggestion_title_this_week, suggest = thisWeekSuggestions)
            }
        }
    }
}

private val yesterdaySuggestions = listOf(
    SearchSuggestion(
        R.drawable.ic_schedule,
        "481 Van Brunt Street",
        "Brooklyn, NY"
    ),
    SearchSuggestion(
        R.drawable.ic_home,
        "Home",
        "199 Pacific Street, Brooklyn, NY"
    )
)

private val thisWeekSuggestions = listOf(
    SearchSuggestion(
        R.drawable.ic_schedule,
        "BEP GA",
        "Forsyth Street, New York, NY"
    ),
    SearchSuggestion(
        R.drawable.ic_schedule,
        "Sushi Nakazawa",
        "Commerce Street, New York, NY"
    ),
    SearchSuggestion(
        R.drawable.ic_schedule,
        "IFC Center",
        "6th Avenue, New York, NY"
    )
)