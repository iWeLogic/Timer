package com.iwelogic.timer_presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.iwelogic.timer_presentation.views.Timer


@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: TimerViewModel = hiltViewModel()
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.onEvent(TimerEvent.OnForegroundStart)
            } else if (event == Lifecycle.Event.ON_STOP) {
                viewModel.onEvent(TimerEvent.OnForegroundStop)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Timer(
            uiState.timerSeconds
        )
        if (uiState.enableStatus) {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    viewModel.onEvent(TimerEvent.OnClickStop)
                }) {
                Text(
                    text = stringResource(R.string.stop),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    viewModel.onEvent(TimerEvent.OnClickStart)
                }) {
                Text(
                    text = stringResource(R.string.start),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

