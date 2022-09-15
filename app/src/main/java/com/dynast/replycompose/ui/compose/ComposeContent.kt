package com.dynast.replycompose.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@Composable
fun ComposeContent(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(start = 4.dp, end = 4.dp, top = 8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize()
//                .weight(1f) // 요건 고민을!!!!
            ,
            shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
            backgroundColor = MaterialTheme.colorScheme.surface
        ) {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                SubjectWidget { item ->
                    if (item == NavItem.Close) onBackPressed()
                }
                Divider(modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp), thickness = Dp.Hairline)
                DropdownMenuWidget()
                Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = Dp.Hairline)
                RecipientWidget()
                Divider(modifier = Modifier.padding(horizontal = 16.dp), thickness = Dp.Hairline)
                MessageFieldWidget()
            }
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
fun ComposeContentPreview() {
    ReplyComposeTheme {
        ComposeContent {

        }
    }
}