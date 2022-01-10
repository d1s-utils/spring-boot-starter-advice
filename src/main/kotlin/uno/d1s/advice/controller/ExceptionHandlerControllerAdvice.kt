/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and contributors.
 */

package uno.d1s.advice.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import uno.d1s.advice.exception.AbstractHttpStatusException
import uno.d1s.advice.util.HttpServletResponseUtil
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ExceptionHandlerControllerAdvice {

    @Autowired
    private lateinit var httpServletResponseUtil: HttpServletResponseUtil

    @ExceptionHandler
    fun handleAbstractHttpStatusException(ex: AbstractHttpStatusException, response: HttpServletResponse) {
        httpServletResponseUtil.sendErrorDto(response) {
            error = ex.message!!
            status = ex.status.value()
        }
    }

    @ExceptionHandler
    fun handleConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse) {
        httpServletResponseUtil.sendErrorDto(response) {
            error = ex.message!!
            status = HttpStatus.BAD_REQUEST.value()
        }
    }
}