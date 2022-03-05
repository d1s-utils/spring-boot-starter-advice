/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and contributors.
 */

package dev.d1s.advice.controller

import dev.d1s.advice.exception.AbstractHttpStatusException
import dev.d1s.teabag.web.sendErrorDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionHandlerControllerAdvice {

    @ExceptionHandler
    fun handleAbstractHttpStatusException(ex: AbstractHttpStatusException, response: HttpServletResponse) {
        response.sendErrorDto {
            error = ex.message!!
            status = ex.status.value()
        }
    }

    @ExceptionHandler
    fun handleConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse) {
        response.sendErrorDto {
            error = ex.message!!
            status = HttpStatus.BAD_REQUEST.value()
        }
    }
}