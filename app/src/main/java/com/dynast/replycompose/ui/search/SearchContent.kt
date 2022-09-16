package com.dynast.replycompose.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dynast.replycompose.R
import com.dynast.replycompose.data.search.thisWeekSuggestions
import com.dynast.replycompose.data.search.yesterdaySuggestions
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@Composable
fun SearchContent(
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            SuggestWidget(title = R.string.search_suggestion_title_yesterday, suggest = yesterdaySuggestions)
        }
        item {
            SuggestWidget(title = R.string.search_suggestion_title_this_week, suggest = thisWeekSuggestions)
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night",
    backgroundColor = 0xFF000000
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Day"
)
@Composable
fun SearchContentPreview() {
    ReplyComposeTheme {
        SearchContent()
    }
}