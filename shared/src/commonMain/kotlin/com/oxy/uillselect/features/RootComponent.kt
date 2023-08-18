package com.oxy.uillselect.features

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.oxy.uillselect.features.account.AccountComponent
import com.oxy.uillselect.features.account.AccountRoute
import com.oxy.uillselect.features.account.DefaultAccountComponent
import com.oxy.uillselect.features.comment.CommentComponent
import com.oxy.uillselect.features.comment.CommentRoute
import com.oxy.uillselect.features.comment.DefaultCommentComponent
import com.oxy.uillselect.features.profile.DefaultProfileComponent
import com.oxy.uillselect.features.profile.ProfileComponent
import com.oxy.uillselect.features.profile.ProfileRoute
import com.oxy.uillselect.features.selector.DefaultSelectorComponent
import com.oxy.uillselect.features.selector.SelectorComponent
import com.oxy.uillselect.features.selector.SelectorRoute

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>
    @get:Composable
    val topLevelDestination: TopLevelDestination?
    fun navigate(transformer: List<Destination>.() -> List<Destination>)
    fun pop()

    // Defines all possible child components
    sealed class Child {
        class Selector(val component: SelectorComponent) : Child()
        class Account(val component: AccountComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
        class Comment(val component: CommentComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Destination>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Destination.Selector, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )
    override val topLevelDestination: TopLevelDestination?
        @Composable
        get() {
            val destination = stack.value.active.configuration as Destination
            return destination.asTopLevelDestination()
        }

    private fun child(destination: Destination, componentContext: ComponentContext): RootComponent.Child =
        when (destination) {
            is Destination.Selector -> RootComponent.Child.Selector(selectorComponent(componentContext))
            is Destination.Account -> RootComponent.Child.Account(accountComponent(componentContext))
            is Destination.Profile -> RootComponent.Child.Profile(profileComponent(componentContext))
            is Destination.Comment -> RootComponent.Child.Comment(commentComponent(componentContext))
        }

    private fun selectorComponent(componentContext: ComponentContext): SelectorComponent =
        DefaultSelectorComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    private fun accountComponent(componentContext: ComponentContext): AccountComponent =
        DefaultAccountComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    private fun profileComponent(componentContext: ComponentContext): ProfileComponent =
        DefaultProfileComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    private fun commentComponent(componentContext: ComponentContext): CommentComponent =
        DefaultCommentComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    override fun navigate(transformer: List<Destination>.() -> List<Destination>) {
        navigation.navigate(transformer)
    }

    override fun pop() {
        navigation.pop()
    }
}

@Parcelize
sealed interface Destination : Parcelable {

    data object Selector : Destination

    data object Account : Destination

    data object Profile : Destination

    data object Comment : Destination
}


enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: String,
    val titleText: String
) {
    Selector(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconText = "主页",
        titleText = "主页"
    ),
    Account(
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        iconText = "设置",
        titleText = "设置"
    )
}

fun TopLevelDestination.asDestination(): Destination = when (this) {
    TopLevelDestination.Selector -> Destination.Selector
    TopLevelDestination.Account -> Destination.Account
}

fun Destination.asTopLevelDestination(): TopLevelDestination? = when (this) {
    Destination.Selector -> TopLevelDestination.Selector
    Destination.Account -> TopLevelDestination.Account
    else -> null
}


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
            is RootComponent.Child.Selector -> SelectorRoute(component = child.component)
            is RootComponent.Child.Account -> AccountRoute(component = child.component)
            is RootComponent.Child.Profile -> ProfileRoute(component = child.component)
            is RootComponent.Child.Comment -> CommentRoute(component = child.component)
        }
    }
}