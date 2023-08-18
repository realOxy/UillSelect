package com.oxy.uillselect.features.selector

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun SelectorRoute(
    modifier: Modifier = Modifier,
    component: SelectorComponent
) {
    val model by component.value.subscribeAsState()
    SelectorScreen(
        value = model.value,
        onValue = { component.onEvent(SelectorEvent.OnValue) },
        modifier = modifier
    )
}

@Composable
private fun SelectorScreen(
    value: Int,
    onValue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(value.toString())
        Button(onClick = onValue) {
            Text("Press")
        }
    }
}