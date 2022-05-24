package dev.d1s.advice.mapper

import dev.d1s.advice.entity.ErrorResponseData

public interface ExceptionMapper {

    public fun map(exception: Exception): ErrorResponseData?
}