package dev.d1s.advice.autoconfiguration

import dev.d1s.advice.controller.ExceptionHandlerControllerAdvice
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(JacksonAutoConfiguration::class)
class AdviceAutoConfiguration {

    @Bean
    fun exceptionHandlerControllerAdvice() = ExceptionHandlerControllerAdvice()
}