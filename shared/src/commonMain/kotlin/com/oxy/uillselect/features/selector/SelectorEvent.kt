package com.oxy.uillselect.features.selector

sealed class SelectorEvent {
    data object OnValue : SelectorEvent()
}