package dev.d1s.advice.exception

import dev.d1s.advice.domain.ErrorResponseData

public open class HttpStatusException(internal val data: ErrorResponseData) :
    RuntimeException(data.message)