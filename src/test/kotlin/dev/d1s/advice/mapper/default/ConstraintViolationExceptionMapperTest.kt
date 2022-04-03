package dev.d1s.advice.mapper.default

import dev.d1s.advice.mapper.defaultImpl.ConstraintViolationExceptionMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import strikt.api.expectThat

@SpringBootTest
@ContextConfiguration(classes = [ConstraintViolationExceptionMapper::class])
internal class ConstraintViolationExceptionMapperTest {

    @Autowired
    private lateinit var mapper: ConstraintViolationExceptionMapper

    @Test
    fun `should map ConstraintViolationException with valid response`() {
        expectThat(mapper)
    }
}