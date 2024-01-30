package org.example

import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Producer (
    private val messageTemplate: ServiceBusTemplate
){
    val log: Logger = LoggerFactory.getLogger(Producer::class.java)

    @GetMapping("/sendtest")
    fun sendtest() {
        send(SampleRequest("test","test"))
    }

    @PostMapping("/send")
    fun send(@RequestBody request: SampleRequest) {

        //val payloadString = messageConverter.writeValueAsString(request)

        val headers = mapOf(
            MessageHeaders.CONTENT_TYPE to "application/json",
            "foo" to request.foo,
            "bar" to request.bar
        )

        val message : Message<SampleRequest> = MessageBuilder.withPayload(request).copyHeaders(headers).build()

        log.info("Sending message to queue: $message")

        messageTemplate.sendAsync("test-topic2", message).subscribe()
    }
}