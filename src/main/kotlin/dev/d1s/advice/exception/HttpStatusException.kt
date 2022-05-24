package dev.d1s.advice.exception

import dev.d1s.advice.entity.ErrorResponseData

public open class HttpStatusException(internal val data: ErrorResponseData) :
    RuntimeException(data.message)