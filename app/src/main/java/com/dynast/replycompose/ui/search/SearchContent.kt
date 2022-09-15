package com.dynast.replycompose.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.temp.search.thisWeekSuggestions
import com.dynast.replycompose.temp.search.yesterdaySuggestions
import com.dynast.replycompose.ui.components.TopBarWidget
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                TopBarWidget { item ->
                    when (item) {
                        NavItem.Close -> onBackClicked()
                        else -> Unit
                    }
                }
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f), thickness = 1.dp)
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

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Day"
)
@Composable
fun SearchContentPreview() {
    ReplyComposeTheme {
        SearchContent {

        }
    }
}