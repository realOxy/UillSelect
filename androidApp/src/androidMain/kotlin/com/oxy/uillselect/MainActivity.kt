package com.oxy.uillselect

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.oxy.uillselect.core.arch.DefaultRootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )
        setContent {
            MainView(root)
        }
    }
}