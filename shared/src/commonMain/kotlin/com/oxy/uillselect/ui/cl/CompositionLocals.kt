package com.oxy.uillselect.ui.cl

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalTheme = compositionLocalOf { noCompositionLocalProvided<Theme>() }

val LocalSpacing = staticCompositionLocalOf { noCompositionLocalProvided<Spacing>() }

private inline fun <reified T> noCompositionLocalProvided(): T {
    error("Please provide ${T::class.simpleName} in the scope!")
}