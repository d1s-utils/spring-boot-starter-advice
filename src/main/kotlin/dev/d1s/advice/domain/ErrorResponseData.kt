package dev.d1s.advice.domain

import org.springframework.http.HttpStatus

public data class ErrorResponseData(
    val status: HttpStatus,
    val message: String
)