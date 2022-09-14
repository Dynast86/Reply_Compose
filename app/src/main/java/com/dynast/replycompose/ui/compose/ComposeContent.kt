package com.dynast.replycompose.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ComposeContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 4.dp, end = 4.dp, top = 8.dp),
    ) {
        item {
            Card(modifier = Modifier.fillParentMaxSize()) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    SubjectWidget {}
                    Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), thickness = Dp.Hairline)
                    DropdownMenuWidget()
                    Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), thickness = Dp.Hairline)
//                    Spinner
                }
            }
        }
    }
}