package dev.d1s.advice.controller

import com.ninjasquad.springmockk.MockkBean
import dev.d1s.advice.domain.ErrorResponseData
import dev.d1s.advice.exception.HttpStatusException
import dev.d1s.advice.mapper.ExceptionMapper
import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.teabag.testing.spring.web.http.mockResponse
import dev.d1s.teabag.web.sendErrorDto
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [ExceptionHandlerControllerAdvice::class])
internal class ExceptionHandlerControllerAdviceTest {

    @Autowired
    private lateinit var exceptionHandlerControllerAdvice: ExceptionHandlerControllerAdvice

    @MockkBean
    private lateinit var mapper: ExceptionMapper

    private val testException = RuntimeException(VALID_STUB)

    private val response = mockResponse

    private val testErrorResponseData = ErrorResponseData(
        HttpStatus.BAD_REQUEST,
        VALID_STUB
    )

    @Test
    fun `should handle exception`() {
        every {
            mapper.map(testException)
        } returns testErrorResponseData

        this.withStaticMock {
            exceptionHandlerControllerAdvice.handleException(
                testException,
                response
            )
        }

        verify {
            mapper.map(testException)
        }
    }

    @Test
    fun `should handle HttpStatusException`() {
        this.withStaticMock {
            exceptionHandlerControllerAdvice.handleException(
                HttpStatusException(
                    testErrorResponseData
                ),
                response
            )
        }
    }

    @Test
    fun `should throw exception if there is no suitable mapper for it`() {
        every {
            mapper.map(testException)
        } returns null

        assertThrows<RuntimeException> {
            exceptionHandlerControllerAdvice.handleException(testException, response)
        }
    }

    private inline fun withStaticMock(block: () -> Unit) {
        mockkStatic("dev.d1s.teabag.web.HttpServletResponseExtKt") {
            justRun {
                response.sendErrorDto(any())
            }

            assertDoesNotThrow {
                block()
            }

            verify {
                response.sendErrorDto(any())
            }
        }
    }
}