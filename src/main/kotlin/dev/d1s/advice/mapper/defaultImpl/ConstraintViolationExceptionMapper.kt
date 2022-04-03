package dev.d1s.advice.mapper.defaultImpl

import dev.d1s.advice.domain.ErrorResponseData
import dev.d1s.advice.mapper.ExceptionMapper
import org.springframework.http.HttpStatus
import javax.validation.ConstraintViolationException

internal class ConstraintViolationExceptionMapper : ExceptionMapper {

    override fun map(exception: Exception): ErrorResponseData? = if (exception is ConstraintViolationException) {
        ErrorResponseData(HttpStatus.BAD_REQUEST, exception.message!!)
    } else {
        null
    }
}