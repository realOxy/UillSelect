package com.oxy.uillselect.data

import app.cash.sqldelight.db.SqlDriver
import com.oxy.uillselect.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)

    // Do more work with the database (see below).
    return database
}