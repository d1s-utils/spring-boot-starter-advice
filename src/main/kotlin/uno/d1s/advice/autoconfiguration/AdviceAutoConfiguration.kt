package uno.d1s.advice.autoconfiguration

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import uno.d1s.advice.controller.ExceptionHandlerControllerAdvice
import uno.d1s.advice.util.HttpServletResponseUtil

@Configuration
@Import(JacksonAutoConfiguration::class)
class AdviceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun exceptionHandlerControllerAdvice() = ExceptionHandlerControllerAdvice()

    @Bean
    @ConditionalOnMissingBean
    fun httpServletResponseUtil() = HttpServletResponseUtil()
}