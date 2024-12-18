package com.example.snoozeloo.domain.util

interface DataError : Error {
    enum class Local: DataError {
        DISK_FULL
    }
}