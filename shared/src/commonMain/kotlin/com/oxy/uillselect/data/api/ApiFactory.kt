package com.oxy.uillselect.data.api

interface ApiFactory {
    fun <T> createApi(): T
}