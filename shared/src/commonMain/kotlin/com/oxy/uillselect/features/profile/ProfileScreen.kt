package com.oxy.uillselect.features.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
    component: ProfileComponent
) {
    val model by component.value.subscribeAsState()
    ProfileScreen(
        modifier = modifier
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier
) {
}