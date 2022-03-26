package dev.d1s.advice.controller

import dev.d1s.advice.exception.HttpStatusException
import dev.d1s.teabag.testing.spring.http.mockResponse
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

@SpringBootTest
@ContextConfiguration(classes = [HttpStatusExceptionHandlerControllerAdvice::class])
internal class HttpStatusExceptionHandlerControllerAdviceTest {

    @Autowired
    private lateinit var httpStatusExceptionHandlerControllerAdvice: HttpStatusExceptionHandlerControllerAdvice

    @Test
    fun `should handle HttpStatusException`() {
        val response = mockResponse
        val testHttpStatusException = HttpStatusException(HttpStatus.CONFLICT, "Uh oh")

        mockkStatic("dev.d1s.teabag.web.HttpServletResponseKt") {
            justRun {
                response.sendErrorDto(any())
            }

            assertDoesNotThrow {
                httpStatusExceptionHandlerControllerAdvice.handleHttpStatusException(testHttpStatusException, response)
            }

            verify {
                response.sendErrorDto(any())
            }
        }
    }
}