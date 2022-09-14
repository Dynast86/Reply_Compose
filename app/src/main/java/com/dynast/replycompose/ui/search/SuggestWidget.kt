package com.dynast.replycompose.ui.search

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.temp.search.SearchSuggestion

@Composable
fun SuggestWidget(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    suggest: List<SearchSuggestion>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 4.dp),
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        repeat(suggest.size) {
            SuggestItemWidget(item = suggest[it])
        }
    }
}
