package org.example

import com.azure.spring.messaging.ConsumerIdentifier
import com.azure.spring.messaging.PropertiesSupplier
import com.azure.spring.messaging.servicebus.core.properties.ProcessorProperties
import com.azure.spring.messaging.servicebus.core.properties.ProducerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("cloud.azure.servicebus")
data class ServiceBusConsumerProperties (
    val namespace: String,
    val consumer: Map<String, ProcessorProperties>,
) : PropertiesSupplier<ConsumerIdentifier, ProcessorProperties> {
    override fun getProperties(identifier: ConsumerIdentifier): ProcessorProperties {
        val properties = this.consumer[identifier.destination] ?: ProcessorProperties()

        //Overwritten properties
        properties.autoComplete = false

        return properties
    }
}
@ConstructorBinding
@ConfigurationProperties("cloud.azure.servicebus")
data class ServiceBusProducerProperties (
    val namespace: String,
    val producer: Map<String, ProducerProperties>,
) : PropertiesSupplier<String, ProducerProperties> {
    override fun getProperties(identifier: String): ProducerProperties? {
        return this.producer[identifier]
    }
}