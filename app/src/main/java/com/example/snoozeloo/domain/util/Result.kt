package com.example.snoozeloo.domain.util

typealias AlarmDataError = Error
sealed interface Result<out D, out E:Error> {
    data class Success<out D>(val data: D) : Result<D,Nothing>
    data class Error<out E:AlarmDataError>(val error: E) : Result<Nothing,E>
}