package com.dynast.replycompose.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dynast.replycompose.ui.nav.NavItem

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onClick: (NavItem) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            Button(onClick = {
                onClick(NavItem.Search)
            }) {
                Text(text = "Search")
            }
            Button(onClick = {
                onClick(NavItem.Compose)
            }) {
                Text(text = "Compose")
            }
            Button(onClick = { onClick(NavItem.Email) }) {
                Text(text = "Email")
            }
        }
    }
}


