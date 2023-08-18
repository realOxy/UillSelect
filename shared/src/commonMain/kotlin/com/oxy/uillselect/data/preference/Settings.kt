package com.oxy.uillselect.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

typealias Settings = DataStore<Preferences>

expect val settings: Settings

internal const val dataStoreFileName = "settings.preferences_pb"