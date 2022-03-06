package dev.d1s.advice.autoconfiguration

import dev.d1s.advice.controller.ConstraintViolationExceptionHandlerControllerAdvice
import dev.d1s.advice.controller.HttpStatusExceptionHandlerControllerAdvice
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(JacksonAutoConfiguration::class)
public class AdviceAutoConfiguration {

    @Bean
    internal fun constraintViolationExceptionHandlerControllerAdvice() =
        ConstraintViolationExceptionHandlerControllerAdvice()

    @Bean
    internal fun httpStatusExceptionHandlerControllerAdvice() =
        HttpStatusExceptionHandlerControllerAdvice()
}