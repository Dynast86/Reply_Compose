package com.dynast.replycompose.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.temp.compose.Account

@Composable
fun DropdownMenuWidget(
    modifier: Modifier = Modifier
) {
    var isOpen by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 8.dp)
            .clickable { isOpen = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isOpen, onDismissRequest = { isOpen = false }) {
            allUserAccounts.forEach {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { isOpen = false }
                ) {
                    Text(text = it.email)
                }
            }
        }
        IconButton(onClick = { isOpen = true }) {
            Icon(painter = painterResource(id = R.drawable.ic_arrow_drop_down), contentDescription = "Dropdown")
        }
    }
}

private val allUserAccounts = mutableListOf(
    Account(
        1L,
        0L,
        "Jeff",
        "Hansen",
        "hikingfan@gmail.com",
        "hkngfan@outside.com",
        R.drawable.avatar_10,
        true
    ),
    Account(
        2L,
        0L,
        "Jeff",
        "H",
        "jeffersonloveshiking@gmail.com",
        "jeffersonloveshiking@work.com",
        R.drawable.avatar_2
    ),
    Account(
        3L,
        0L,
        "Jeff",
        "Hansen",
        "jeffersonc@google.com",
        "jeffersonc@gmail.com",
        R.drawable.avatar_9
    )
)