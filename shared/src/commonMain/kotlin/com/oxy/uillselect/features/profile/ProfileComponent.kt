package com.oxy.uillselect.features.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface ProfileComponent {
    val value: Value<ProfileModel>
    fun onEvent(event: ProfileEvent)
}

class DefaultProfileComponent(
    componentContext: ComponentContext,
    onFinished: () -> Unit
) : ProfileComponent {
    override val value: Value<ProfileModel> = MutableValue(ProfileModel())

    override fun onEvent(event: ProfileEvent) {

    }
}