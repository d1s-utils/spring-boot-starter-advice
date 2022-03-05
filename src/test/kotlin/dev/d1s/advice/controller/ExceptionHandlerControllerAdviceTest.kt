/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and contributors.
 */

package dev.d1s.advice.controller

import dev.d1s.advice.exception.AbstractHttpStatusException
import dev.d1s.teabag.testing.mockResponse
import dev.d1s.teabag.web.sendErrorDto
import io.mockk.justRun
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import javax.validation.ConstraintViolationException

@SpringBootTest
@ContextConfiguration(classes = [ExceptionHandlerControllerAdvice::class])
class ExceptionHandlerControllerAdviceTest {

    @Autowired
    private lateinit var exceptionHandlerControllerAdvice: ExceptionHandlerControllerAdvice

    private val response = mockResponse
    private val testHttpStatusException = object : AbstractHttpStatusException(HttpStatus.CONFLICT, "Uh oh") {}
    private val testConstraintViolationException: ConstraintViolationException =
        ConstraintViolationException(setOf())

    @Test
    fun `should handle abstract status code exception`() {
        mockResponseUtil {
            assertDoesNotThrow {
                exceptionHandlerControllerAdvice.handleAbstractHttpStatusException(testHttpStatusException, response)
            }
        }
    }

    @Test
    fun `should handle constraint violation exception`() {
        mockResponseUtil {
            assertDoesNotThrow {
                exceptionHandlerControllerAdvice.handleConstraintViolation(testConstraintViolationException, response)
            }
        }
    }

    private inline fun mockResponseUtil(block: () -> Unit) {
        mockkStatic("dev.d1s.teabag.web.HttpServletResponseKt") {
            justRun {
                response.sendErrorDto(any())
            }

            block()

            verify {
                response.sendErrorDto(any())
            }
        }
    }
}