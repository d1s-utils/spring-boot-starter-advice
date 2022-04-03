package dev.d1s.advice.mapper.default

import dev.d1s.advice.domain.ErrorResponseData
import dev.d1s.advice.mapper.defaultImpl.ConstraintViolationExceptionMapper
import dev.d1s.teabag.testing.constant.VALID_STUB
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull
import javax.validation.ConstraintViolationException

@SpringBootTest
@ContextConfiguration(classes = [ConstraintViolationExceptionMapper::class])
internal class ConstraintViolationExceptionMapperTest {

    @Autowired
    private lateinit var mapper: ConstraintViolationExceptionMapper

    @Test
    fun `should map ConstraintViolationException with valid response`() {
        val constraintViolationException = ConstraintViolationException(VALID_STUB, null)

        expectThat(
            mapper.map(constraintViolationException)
        ) isEqualTo ErrorResponseData(HttpStatus.BAD_REQUEST, VALID_STUB)
    }

    @Test
    fun `should map RuntimeException with null`() {
        expectThat(
            mapper.map(RuntimeException())
        ).isNull()
    }
}