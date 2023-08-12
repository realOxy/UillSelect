package com.oxy.uillselect.data

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

private val delegate: Preferences = Preferences.userRoot()
actual val settings: Settings by lazy {
    PreferencesSettings(delegate)
}