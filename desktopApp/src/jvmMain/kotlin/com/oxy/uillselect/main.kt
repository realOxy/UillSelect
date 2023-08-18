package com.oxy.uillselect

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.oxy.uillselect.features.DefaultRootComponent
import javax.swing.SwingUtilities

private const val WINDOW_TITLE = "UillSelect"

fun main() {
    val lifecycle = LifecycleRegistry()

    val root = runOnUiThread {
        DefaultRootComponent(DefaultComponentContext(lifecycle))
    }

    application {
        val windowState = rememberWindowState()
        Window(
            state = windowState,
            onCloseRequest = ::exitApplication,
            title = WINDOW_TITLE,
            icon = painterResource("compose-multiplatform.xml")
        ) {
            MainView(root)
        }
    }
}

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}