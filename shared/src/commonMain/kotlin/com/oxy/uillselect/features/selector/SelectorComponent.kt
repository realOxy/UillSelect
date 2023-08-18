package com.oxy.uillselect.features.selector

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update

interface SelectorComponent {
    val value: Value<SelectorModel>
    fun onEvent(event: SelectorEvent)
}

class DefaultSelectorComponent(
    componentContext: ComponentContext,
    onFinished: () -> Unit
) : SelectorComponent {
    private val _value = MutableValue(SelectorModel())
    override val value: Value<SelectorModel> get() = _value

    override fun onEvent(event: SelectorEvent) {
        when (event) {
            SelectorEvent.OnValue -> onValue()
        }
    }

    private fun onValue() {
        _value.update { it.copy(value = it.value + 1) }
    }
}