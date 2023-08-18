package com.oxy.uillselect.data.preference

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import java.io.File


actual val settings: Settings = PreferenceDataStoreFactory.create {
    File(appDir, dataStoreFileName)
}

private val appDir: File
    get() {
        val os = System.getProperty("os.name").lowercase()
        val file = when {
            os.contains("win") -> {
                File(System.getenv("AppData"), "uillselect/db")
            }

            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                File(System.getProperty("user.home"), ".uillselect")
            }

            os.contains("mac") -> {
                File(System.getProperty("user.home"), "Library/Application Support/Uillselect")
            }

            else -> error("Unsupported operating system")
        }
        if (!file.exists()) file.mkdirs()
        return file
    }