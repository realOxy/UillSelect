package com.oxy.uillselect.features.account

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface AccountComponent {
    val value: Value<AccountModel>
    fun onEvent(event: AccountEvent)
}

class DefaultAccountComponent(
    componentContext: ComponentContext,
    onFinished: () -> Unit
) : AccountComponent {
    override val value: Value<AccountModel> = MutableValue(AccountModel())

    override fun onEvent(event: AccountEvent) {

    }
}