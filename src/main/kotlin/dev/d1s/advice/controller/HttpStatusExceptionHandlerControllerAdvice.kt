package dev.d1s.advice.controller

import dev.d1s.advice.exception.HttpStatusException
import dev.d1s.teabag.web.sendErrorDto
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
internal class HttpStatusExceptionHandlerControllerAdvice {

    @ExceptionHandler
    fun handleHttpStatusException(ex: HttpStatusException, response: HttpServletResponse) {
        response.sendErrorDto {
            error = ex.message!!
            status = ex.status.value()
        }
    }
}