package dev.d1s.advice.exception

import dev.d1s.advice.entity.ErrorResponseData
import org.springframework.http.HttpStatus

public class UnprocessableEntityException(message: String) : HttpStatusException(
    ErrorResponseData(HttpStatus.UNPROCESSABLE_ENTITY, message)
)