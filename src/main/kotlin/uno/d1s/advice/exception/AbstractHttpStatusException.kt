/*
 * BSD 3-Clause License, Copyright (c) 2021, Pulseq and contributors.
 */

package uno.d1s.advice.exception

import org.springframework.http.HttpStatus

abstract class AbstractHttpStatusException(override val status: HttpStatus, message: String) :
    RuntimeException(message), HttpStatusException