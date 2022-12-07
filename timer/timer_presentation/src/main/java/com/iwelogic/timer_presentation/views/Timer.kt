package com.iwelogic.timer_presentation.views

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Timer(seconds: Long, modifier: Modifier = Modifier) {
    val displayHours = seconds / 3600
    val displayMinutes = seconds / 60 - displayHours * 60
    val displaySeconds = seconds % 60
    Text(
        modifier = modifier,
        text = String.format("%02d:%02d:%02d", displayHours, displayMinutes, displaySeconds),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
}