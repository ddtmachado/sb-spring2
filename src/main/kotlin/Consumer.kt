package org.example

import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext
import com.azure.spring.messaging.implementation.annotation.EnableAzureMessaging
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener
import com.azure.spring.messaging.servicebus.support.ServiceBusMessageHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@EnableAzureMessaging
class Consumer {
    private val log: Logger = LoggerFactory.getLogger(ConsumerConfig::class.java)

    @ServiceBusListener(
        destination = "test-topic2",
        containerFactory = "containerFactory",
        group = "sub1"
    )
    fun onMessage(
        @Payload payload: SampleRequest,
        @Header(ServiceBusMessageHeaders.RECEIVED_MESSAGE_CONTEXT) context: ServiceBusReceivedMessageContext
    ) {
        log.info("Payload: ${payload}")

        if (payload.foo.equals("error")) {
            throw ExceptionWithServiceBusContext("Test exception", context)
        }
        log.info("Context: ${context}")

        context.complete()
        log.info("Message completed")
    }

}