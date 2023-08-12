package com.oxy.uillselect.features.selector

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun SelectorRoute(
    modifier: Modifier = Modifier,
    component: SelectorComponent
) {
    val model by component.value.subscribeAsState()
    SelectorScreen(
        modifier = modifier
    )
}

@Composable
private fun SelectorScreen(
    modifier: Modifier = Modifier
) {
}