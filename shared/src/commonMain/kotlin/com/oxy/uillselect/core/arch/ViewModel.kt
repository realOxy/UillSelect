package com.oxy.uillselect.core.arch

abstract class ViewModel<in E> {
    abstract fun onEvent(event: E)
}

fun <VM: ViewModel<*>> viewModels(): VM {
    TODO()
}