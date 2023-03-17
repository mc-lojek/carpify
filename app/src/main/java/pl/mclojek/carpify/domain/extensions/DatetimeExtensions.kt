package pl.mclojek.carpify.domain.extensions

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val DATE_READABLE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy")
private val TIME_READABLE_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
private val DATETIME_READABLE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

fun ZonedDateTime.toDateReadable(): String {
    return this.format(DATE_READABLE_FORMATTER)
}

fun ZonedDateTime.toTimeReadable(): String {
    return this.format(TIME_READABLE_FORMATTER)
}

fun ZonedDateTime.toDatetimeReadable(): String {
    return this.format(DATETIME_READABLE_FORMATTER)
}