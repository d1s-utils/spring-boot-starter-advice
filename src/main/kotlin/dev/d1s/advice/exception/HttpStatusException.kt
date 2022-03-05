/*
 * BSD 3-Clause License, Copyright (c) 2021, Pulseq and contributors.
 */

package dev.d1s.advice.exception

import org.springframework.http.HttpStatus

interface HttpStatusException {

    val status: HttpStatus
}