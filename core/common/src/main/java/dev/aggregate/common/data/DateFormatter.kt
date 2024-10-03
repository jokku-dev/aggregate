package dev.aggregate.common.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToString(): String {
    val dateFormat = SimpleDateFormat("dd.MMM HH.mm", Locale.getDefault())
    return dateFormat.format(this)
}
