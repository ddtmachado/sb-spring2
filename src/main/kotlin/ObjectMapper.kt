package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter

@Configuration
open class ObjectMapperConfig {

    @Bean
    open fun messageConverter(): MappingJackson2MessageConverter {
        val messageConverter = MappingJackson2MessageConverter()
        messageConverter.objectMapper = objectMapper()

        return messageConverter
    }

    @Bean
    open fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }
}