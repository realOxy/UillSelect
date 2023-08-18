package com.oxy.uillselect.features.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun AccountRoute(
    modifier: Modifier = Modifier,
    component: AccountComponent
) {
    val model by component.value.subscribeAsState()
    AccountScreen(
        modifier = modifier
    )
}

@Composable
private fun AccountScreen(
    modifier: Modifier = Modifier
) {
}