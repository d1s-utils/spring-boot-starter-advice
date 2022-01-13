/*
 * BSD 3-Clause License, Copyright (c) 2022, Mikhail Titov and other contributors.
 */

package uno.d1s.advice.dto

import org.springframework.http.HttpStatus
import uno.d1s.advice.util.currentRequest
import java.time.Instant

data class ErrorDto(
    var timestamp: Instant = Instant.now(),
    var status: Int = HttpStatus.INTERNAL_SERVER_ERROR.value(),
    var error: String = "Something went wrong.",
    var path: String = currentRequest.pathInfo ?: "/"
)