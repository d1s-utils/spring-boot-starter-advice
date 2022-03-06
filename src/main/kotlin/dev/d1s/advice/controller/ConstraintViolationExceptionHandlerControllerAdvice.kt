package dev.d1s.advice.controller

import dev.d1s.advice.exception.HttpStatusException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
internal class ConstraintViolationExceptionHandlerControllerAdvice {

    @ExceptionHandler
    fun handleConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse) {
        throw HttpStatusException(HttpStatus.BAD_REQUEST, ex.message!!)
    }
}
