package org.example

import com.azure.spring.cloud.service.servicebus.properties.ServiceBusEntityType
import com.azure.spring.messaging.servicebus.core.DefaultServiceBusNamespaceProcessorFactory
import com.azure.spring.messaging.servicebus.core.ServiceBusProcessorFactory
import com.azure.spring.messaging.servicebus.core.properties.NamespaceProperties
import com.azure.spring.messaging.servicebus.implementation.core.config.ServiceBusMessageListenerContainerFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ServiceBusConsumerProperties::class)
open class ConsumerConfig (
    private val properties: ServiceBusConsumerProperties
) {
    val log: Logger = LoggerFactory.getLogger(ConsumerConfig::class.java)
    @Bean
    open fun containerFactory(): ServiceBusMessageListenerContainerFactory {
        val namespace = NamespaceProperties()
        namespace.namespace = properties.namespace
        namespace.entityType = ServiceBusEntityType.TOPIC

        val processorFactory: ServiceBusProcessorFactory = DefaultServiceBusNamespaceProcessorFactory(namespace, properties)

        val containerFactory = ServiceBusMessageListenerContainerFactory(processorFactory)
        containerFactory.setErrorHandler(ConsumerExceptionHandler())

        return containerFactory
    }
}