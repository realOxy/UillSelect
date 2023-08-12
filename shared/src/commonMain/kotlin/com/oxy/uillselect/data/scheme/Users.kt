package com.oxy.uillselect.data.scheme

object Users {
    object Role {
        // multi-admin is not allowed now!
        const val ADMIN = 0L
        const val CONTRACT = 1L
        const val OTHER = 2L
    }
}