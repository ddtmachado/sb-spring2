package org.example

import com.azure.spring.cloud.service.servicebus.properties.ServiceBusEntityType
import com.azure.spring.messaging.servicebus.core.DefaultServiceBusNamespaceProducerFactory
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate
import com.azure.spring.messaging.servicebus.core.properties.NamespaceProperties
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import com.fasterxml.jackson.module.kotlin.KotlinModule

@Configuration
@EnableConfigurationProperties(ServiceBusProducerProperties::class)
open class ProducerConfig (
    private val properties: ServiceBusProducerProperties,
) {
    val log: Logger = LoggerFactory.getLogger(ProducerConfig::class.java)

    @Bean
    open fun serviceBusTemplate(): ServiceBusTemplate {
        val namespace = NamespaceProperties()
        namespace.namespace = properties.namespace
        namespace.entityType = ServiceBusEntityType.TOPIC
        val producerFactory = DefaultServiceBusNamespaceProducerFactory(namespace, properties)
//        val credential = DefaultAzureCredentialBuilder()
//           .build()
//        producerFactory.setDefaultCredential(credential)

        return ServiceBusTemplate(producerFactory)
    }
}