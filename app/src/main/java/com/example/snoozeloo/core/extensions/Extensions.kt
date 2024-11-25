package com.example.snoozeloo.core.extensions

fun String?.toSafeString() = this ?: ""
fun Double?.toSafeDouble() = this ?: 0.0
fun Int?.toSafeInt() = this ?: 0

