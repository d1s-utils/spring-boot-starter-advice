/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and other contributors.
 */

package uno.d1s.advice.util

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@SpringBootTest
@ContextConfiguration(classes = [CurrentRequestUtilTest::class])
class CurrentRequestUtilTest {

    @Test
    fun `should return valid current request`() {
        val mockRequest = MockHttpServletRequest()

        mockkStatic(RequestContextHolder::class) {
            every {
                (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            } returns mockRequest

            Assertions.assertEquals(mockRequest, currentRequest)

            verify {
                (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            }
        }
    }
}