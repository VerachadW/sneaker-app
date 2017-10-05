package com.agoda.sneakershop.common.extension

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

@JvmName("showToast")
@JvmOverloads
fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

@JvmName("showToast")
@JvmOverloads
fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    activity.toast(text, length)
}