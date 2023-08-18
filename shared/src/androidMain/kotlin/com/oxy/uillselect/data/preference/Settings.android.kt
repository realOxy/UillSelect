package com.oxy.uillselect.data.preference

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.oxy.uillselect.data.Instances

actual val settings: Settings = PreferenceDataStoreFactory.create(
    corruptionHandler = null,
    migrations = emptyList()
) {
    Instances.app.filesDir.resolve(dataStoreFileName)
}