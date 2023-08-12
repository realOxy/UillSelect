package com.oxy.uillselect.data

import android.app.Application

object Instances {
    lateinit var _app: Application
    val app: Application get() = _app
}