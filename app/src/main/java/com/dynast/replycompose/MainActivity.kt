package com.dynast.replycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dynast.replycompose.ui.main.MainContent
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReplyComposeTheme {
                MainContent()
            }
        }
    }
}