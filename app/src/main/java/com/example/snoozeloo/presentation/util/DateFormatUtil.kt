package com.example.snoozeloo.presentation.util

import android.content.Context
import android.text.format.DateFormat

fun Context.isIn24HourFormat(): Boolean =
    DateFormat.is24HourFormat(this)