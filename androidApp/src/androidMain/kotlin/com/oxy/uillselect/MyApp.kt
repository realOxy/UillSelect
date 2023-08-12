package com.oxy.uillselect

import android.app.Application
import com.oxy.uillselect.data.Instances

class MyApp : Application() {
    init {
        Instances._app = this
    }
}