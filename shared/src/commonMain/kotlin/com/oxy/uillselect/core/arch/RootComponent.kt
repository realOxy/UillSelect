package com.oxy.uillselect.core.arch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.oxy.uillselect.features.selector.DefaultSelectorComponent
import com.oxy.uillselect.features.selector.SelectorComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    // Defines all possible child components
    sealed class Child {
        class SelectorChild(val component: SelectorComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Selector, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Selector -> RootComponent.Child.SelectorChild(selectorComponent(componentContext))
        }

    private fun selectorComponent(componentContext: ComponentContext): SelectorComponent =
        DefaultSelectorComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    override fun onBackClicked() {
        navigation.pop()
    }

    @Parcelize
    private sealed interface Config : Parcelable {
        data object Selector : Config
    }
}