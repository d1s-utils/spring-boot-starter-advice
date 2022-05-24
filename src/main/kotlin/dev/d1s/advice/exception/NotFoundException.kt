package dev.d1s.advice.exception

import dev.d1s.advice.entity.ErrorResponseData
import org.springframework.http.HttpStatus

public open class NotFoundException(message: String) : HttpStatusException(
    ErrorResponseData(HttpStatus.NOT_FOUND, message)
)