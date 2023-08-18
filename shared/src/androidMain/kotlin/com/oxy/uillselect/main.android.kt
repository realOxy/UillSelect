package com.oxy.uillselect

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oxy.uillselect.features.RootComponent
import com.oxy.uillselect.features.TopLevelDestination
import com.oxy.uillselect.features.asDestination
import com.oxy.uillselect.ui.UillSelectTheme

@Composable
fun MainView(component: RootComponent) {
    UillSelectTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationSheet(
                    destinations = TopLevelDestination.entries,
                    onItemClick = {
                        val destination = it.asDestination()
                        component.navigate {
                            listOf(destination)
                        }
                    },
                    index = TopLevelDestination.entries.indexOf(component.topLevelDestination),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { padding ->
            RootComponent(component, Modifier.padding(padding))
        }
    }
}
