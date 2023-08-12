package com.oxy.uillselect.features.selector

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface SelectorComponent {
    val value: Value<SelectorModel>
    fun onEvent(event: SelectorEvent)
}

class DefaultSelectorComponent(
    componentContext: ComponentContext,
    onFinished: () -> Unit
) : SelectorComponent {
    override val value: Value<SelectorModel> = MutableValue(SelectorModel())

    override fun onEvent(event: SelectorEvent) {

    }
}