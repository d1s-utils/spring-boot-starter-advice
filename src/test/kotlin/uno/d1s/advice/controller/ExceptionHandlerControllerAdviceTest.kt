/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and contributors.
 */

package uno.d1s.advice.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ContextConfiguration
import uno.d1s.advice.exception.AbstractHttpStatusException
import uno.d1s.advice.util.HttpServletResponseUtil
import javax.validation.ConstraintViolationException

@SpringBootTest
@ContextConfiguration(classes = [ExceptionHandlerControllerAdvice::class])
class ExceptionHandlerControllerAdviceTest {

    @Autowired
    private lateinit var exceptionHandlerControllerAdvice: ExceptionHandlerControllerAdvice

    @MockkBean(relaxUnitFun = true)
    private lateinit var httpServletResponseUtil: HttpServletResponseUtil

    private val testResponse = MockHttpServletResponse()
    private val testHttpStatusException = object : AbstractHttpStatusException(HttpStatus.CONFLICT, "Uh oh") {}
    private val testConstraintViolationException: ConstraintViolationException =
        ConstraintViolationException(setOf())

    @Test
    fun `should handle abstract status code exception`() {
        assertDoesNotThrow {
            exceptionHandlerControllerAdvice.handleAbstractHttpStatusException(testHttpStatusException, testResponse)
        }

        verify {
            httpServletResponseUtil.sendErrorDto(testResponse, any())
        }
    }

    @Test
    fun `should handle constraint violation exception`() {
        assertDoesNotThrow {
            exceptionHandlerControllerAdvice.handleConstraintViolation(testConstraintViolationException, testResponse)
        }

        verify {
            httpServletResponseUtil.sendErrorDto(testResponse, any())
        }
    }
}