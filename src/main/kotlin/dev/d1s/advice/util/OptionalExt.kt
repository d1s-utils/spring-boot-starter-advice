package dev.d1s.advice.util

import dev.d1s.advice.exception.NotFoundException
import java.util.*

public fun <T> Optional<T>.orElseNotFound(message: String = "Not found."): T =
    this.orElseThrow {
        NotFoundException(message)
    }