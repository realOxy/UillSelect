package com.oxy.uillselect.features

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.oxy.uillselect.core.arch.RootComponent
import com.oxy.uillselect.features.selector.SelectorRoute

@Composable
fun RootComponent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.SelectorChild -> SelectorRoute(component = child.component)
        }
    }
}