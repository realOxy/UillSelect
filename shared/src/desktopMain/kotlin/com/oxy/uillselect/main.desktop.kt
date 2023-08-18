package com.oxy.uillselect

import androidx.compose.runtime.Composable
import com.oxy.uillselect.features.RootComponent
import com.oxy.uillselect.ui.UillSelectTheme

@Composable
fun MainView(rootComponent: RootComponent) {
    UillSelectTheme {
        RootComponent(rootComponent)
    }
}