package dev.d1s.advice.controller

import com.ninjasquad.springmockk.MockkBean
import dev.d1s.advice.domain.ErrorResponseData
import dev.d1s.advice.mapper.ExceptionMapper
import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.teabag.testing.spring.http.mockResponse
import dev.d1s.teabag.web.sendErrorDto
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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

    @Test
    fun `should handle exception`() {
        val testException = RuntimeException(VALID_STUB)

        every {
            mapper.map(testException)
        } returns ErrorResponseData(
            HttpStatus.BAD_REQUEST,
            VALID_STUB
        )

        val spyResponse = spyk(mockResponse)

        mockkStatic("dev.d1s.teabag.web.HttpServletResponseExtKt") {
            justRun {
                spyResponse.sendErrorDto(any())
            }

            assertDoesNotThrow {
                exceptionHandlerControllerAdvice.handleException(testException, spyResponse)
            }

            verify {
                spyResponse.sendErrorDto(any())
            }
        }

        verify {
            mapper.map(testException)
        }
    }
}