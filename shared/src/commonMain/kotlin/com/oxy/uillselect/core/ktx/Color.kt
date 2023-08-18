package com.oxy.uillselect.core.ktx

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

@Composable
fun Color.animated(): State<Color> = animateColorAsState(this)