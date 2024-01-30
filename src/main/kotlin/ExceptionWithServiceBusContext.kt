package org.example

import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext

class ExceptionWithServiceBusContext(
    message: String,
    private val context: ServiceBusReceivedMessageContext
) : Throwable(message) {

    fun getContext() : ServiceBusReceivedMessageContext {
        return context
    }
}