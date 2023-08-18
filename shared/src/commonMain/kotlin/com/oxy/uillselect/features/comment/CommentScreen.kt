package com.oxy.uillselect.features.comment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun CommentRoute(
    modifier: Modifier = Modifier,
    component: CommentComponent
) {
    val model by component.value.subscribeAsState()
    CommentScreen(
        modifier = modifier
    )
}

@Composable
private fun CommentScreen(
    modifier: Modifier = Modifier
) {
}