package dev.d1s.advice.controller

import dev.d1s.advice.mapper.ExceptionMapper
import dev.d1s.teabag.web.sendErrorDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
internal class ExceptionHandlerControllerAdvice {

    @Autowired
    private lateinit var mappers: Set<ExceptionMapper>

    @ExceptionHandler
    fun handleException(ex: Exception, response: HttpServletResponse) {
        val responseData = mappers.firstNotNullOfOrNull {
            it.map(ex)
        }

        responseData?.let {
            response.sendErrorDto {
                error = it.message
                status = it.status.value()
            }
        } ?: run {
            throw ex
        }
    }
}