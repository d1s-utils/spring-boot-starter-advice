package dev.d1s.advice.controller

import dev.d1s.advice.exception.HttpStatusException
import dev.d1s.teabag.testing.spring.http.mockResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import javax.validation.ConstraintViolationException

@SpringBootTest
@ContextConfiguration(classes = [ConstraintViolationExceptionHandlerControllerAdvice::class])
internal class ConstraintViolationExceptionHandlerControllerAdviceTest {

    @Autowired
    private lateinit var handler: ConstraintViolationExceptionHandlerControllerAdvice

    @Test
    fun `should handle ConstraintViolationException`() {
        val testConstraintViolationException =
            ConstraintViolationException(setOf())

        val ex = assertThrows<HttpStatusException> {
            handler.handleConstraintViolation(testConstraintViolationException, mockResponse)
        }

        expectThat(ex.status).isEqualTo(HttpStatus.BAD_REQUEST)
        expectThat(ex.message).isEqualTo(testConstraintViolationException.message)
    }
}