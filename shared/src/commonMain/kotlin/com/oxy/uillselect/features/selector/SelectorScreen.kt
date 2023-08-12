package com.oxy.uillselect.features.selector

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oxy.uillselect.core.arch.viewModels

@Composable
internal fun SelectorRoute(
    modifier: Modifier = Modifier,
    viewModel: SelectorViewModel = viewModels()
) {
    SelectorScreen(
        modifier = modifier
    )
}

@Composable
private fun SelectorScreen(
    modifier: Modifier = Modifier
) {
}