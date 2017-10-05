package com.agoda.sneakershop.common.extension

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

fun FragmentManager.transaction(lambda: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        lambda()
    }.commit()
}