package com.agoda.sneakershop.common.extension

import java.text.DecimalFormat

fun Double.format(precision: Int = 1): String {
    val formatBuilder = StringBuilder("##")
    if (precision > 0) {
        formatBuilder.append(".")
        for (i in 0 until precision) {
            formatBuilder.append("#")
        }
    }
    val formatter = DecimalFormat(formatBuilder.toString())
    return formatter.format(this).toString()
}