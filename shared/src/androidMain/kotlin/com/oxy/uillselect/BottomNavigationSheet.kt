package com.oxy.uillselect

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.oxy.uillselect.core.ktx.animated
import com.oxy.uillselect.features.TopLevelDestination
import com.oxy.uillselect.ui.cl.LocalTheme

@Composable
fun BottomNavigationSheet(
    destinations: List<TopLevelDestination>,
    onItemClick: (TopLevelDestination) -> Unit,
    index: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BottomSheetDefaults.navigationBackgroundColor(),
    contentColor: Color = BottomSheetDefaults.navigationContentColor(),
    selectedColor: Color = BottomSheetDefaults.navigationSelectedColor(),
) {
    val actualBackgroundColor by backgroundColor.animated()
    val actualContentColor by contentColor.animated()
    val actualSelectedColor by selectedColor.animated()
    NavigationSheet(
        modifier = modifier,
        containerColor = actualBackgroundColor,
        contentColor = actualContentColor,
        elevation = LocalAbsoluteElevation.current
    ) {
        destinations.forEachIndexed { i, destination ->
            val selected = i == index
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(destination)
                },
                tint = actualSelectedColor,
                contentDestination = destination.iconText,
                icon = {
                    val icon = if (selected) destination.selectedIcon
                    else destination.unselectedIcon
                    Icon(
                        imageVector = icon,
                        contentDescription = destination.iconText
                    )
                },
                label = {
                    Text(
                        text = destination.iconText,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}


@Composable
private fun NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    contentDestination: String? = null,
    tint: Color
) {
//    PlainTooltipBox(
//        tooltip = { Text(contentDestination.orEmpty()) }
//    ) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        modifier = Modifier
//                .tooltipAnchor()
            .then(modifier),
        enabled = enabled,
        label = label,
        alwaysShowLabel = false,
        selectedContentColor = tint,
        interactionSource = remember { MutableInteractionSource() },
    )
//    }
}

object BottomSheetDefaults {
    @Composable
    fun navigationBackgroundColor() = Color.Black

    @Composable
    fun navigationContentColor() = Color(0xFFEEEEEE)

    @Composable
    fun navigationSelectedColor() = LocalTheme.current.primary
}