package io.appium.android.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import io.appium.android.apis.Shakespeare

class TimeAgoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                VerticalScroller {
                    Column {
                        Shakespeare.TITLES.forEach {
                            TimeAgoItem(it, timeAgoSamples.shuffled()[0])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimeAgoItem(title: String, time: String) {
    Column(
        modifier = Spacing(16.dp)
    ) {
        Text(text = title, style = +themeTextStyle { h6 })
        Text(text = time, style = +themeTextStyle { body2 })
    }
}

val timeAgoSamples = listOf("1m ago", "15m ago", "1 hour ago", "few seconds ago")