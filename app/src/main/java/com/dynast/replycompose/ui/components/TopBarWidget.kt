package com.dynast.replycompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBarWidget(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var textState by remember { mutableStateOf(TextFieldValue()) }
    val actions = KeyboardActions(
        onSearch = { keyboardController?.hide() }
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick("Back") }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .focusRequester(focusRequester = focusRequester),
            value = textState,
            onValueChange = {
                textState = it
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.tertiary),
            decorationBox = { innerTextField ->
                if (textState.text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.search_suggestion_hint),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                innerTextField()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = actions
        )
        IconButton(onClick = { onClick("Mic") }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "Mic",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarWidgetPreview() {
    ReplyComposeTheme {
        TopBarWidget {

        }
    }
}