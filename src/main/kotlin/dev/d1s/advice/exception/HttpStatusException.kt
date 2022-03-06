package dev.d1s.advice.exception

import org.springframework.http.HttpStatus

public class HttpStatusException(public val status: HttpStatus, message: String) :
    RuntimeException(message)