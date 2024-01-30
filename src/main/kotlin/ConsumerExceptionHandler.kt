package org.example

import com.azure.messaging.servicebus.ServiceBusErrorContext
import com.azure.spring.cloud.service.servicebus.consumer.ServiceBusErrorHandler
import org.slf4j.LoggerFactory

class ConsumerExceptionHandler : ServiceBusErrorHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun accept(errorContext: ServiceBusErrorContext?) {
        if (errorContext != null) {
            val ex = findExceptionWithServiceBusContext(errorContext.exception)
            if (ex != null){
                log.error("Received error: ${ex.message} context: ${ex.getContext()}")
                log.error("Abandoning message ID: ${ex.getContext().message.messageId}")
                ex.getContext().abandon()
            } else {
                log.error("Unexpected error")
            }
        }
    }

    private fun findExceptionWithServiceBusContext(throwable: Throwable) : ExceptionWithServiceBusContext? {
        var t: Throwable? = throwable
        while(t != null) {
            if (t is ExceptionWithServiceBusContext) {
                return t
            }
            t = t.cause
        }
        return null
    }
}