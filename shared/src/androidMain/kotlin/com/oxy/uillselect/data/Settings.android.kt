package com.oxy.uillselect.data

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

private val sharedPreferences: SharedPreferences
    get() = Instances.app.getSharedPreferences("sp", Context.MODE_PRIVATE)
actual val settings: Settings by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SharedPreferencesSettings(sharedPreferences) }