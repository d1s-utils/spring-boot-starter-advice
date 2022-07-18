package dev.d1s.advice.mapper.defaultImpl

import dev.d1s.advice.entity.ErrorResponseData
import dev.d1s.advice.mapper.ExceptionMapper
import org.springframework.http.HttpStatus
import javax.validation.ConstraintViolationException

internal class ConstraintViolationExceptionMapper : ExceptionMapper {

    override fun map(exception: Exception): ErrorResponseData? =
        (exception as? ConstraintViolationException)?.let {
            ErrorResponseData(HttpStatus.BAD_REQUEST, it.message ?: "No message.")
        }
}