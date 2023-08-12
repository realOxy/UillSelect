package com.oxy.uillselect.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.oxy.uillselect.ui.cl.LocalSpacing
import com.oxy.uillselect.ui.cl.LocalTheme
import com.oxy.uillselect.ui.cl.SpacingDefaults
import com.oxy.uillselect.ui.cl.ThemeDefaults

@Composable
fun UillSelectTheme(content: @Composable () -> Unit) {
    val theme = when {
        isSystemInDarkTheme() -> ThemeDefaults.Dark
        else -> ThemeDefaults.Light
    }
    val spacing = SpacingDefaults.Default
    MaterialTheme {
        CompositionLocalProvider(
            LocalTheme provides theme,
            LocalSpacing provides spacing,
            content = content
        )
    }
}