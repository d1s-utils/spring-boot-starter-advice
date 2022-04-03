package dev.d1s.advice.autoconfiguration

import dev.d1s.advice.controller.ExceptionHandlerControllerAdvice
import dev.d1s.advice.controller.HttpStatusExceptionHandlerControllerAdvice
import dev.d1s.advice.mapper.ExceptionMapper
import dev.d1s.advice.mapper.defaultImpl.ConstraintViolationExceptionMapper
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(JacksonAutoConfiguration::class)
public class AdviceAutoConfiguration {

    @Bean
    internal fun exceptionHandlerControllerAdvice() =
        ExceptionHandlerControllerAdvice()

    @Bean
    internal fun httpStatusExceptionHandlerControllerAdvice() =
        HttpStatusExceptionHandlerControllerAdvice()

    @Bean
    internal fun constraintViolationExceptionMapper(): ExceptionMapper =
        ConstraintViolationExceptionMapper()
}