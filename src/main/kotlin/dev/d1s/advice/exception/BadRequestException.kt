package dev.d1s.advice.exception

import dev.d1s.advice.entity.ErrorResponseData
import org.springframework.http.HttpStatus

public open class BadRequestException(message: String) : HttpStatusException(
    ErrorResponseData(HttpStatus.BAD_REQUEST, message)
)