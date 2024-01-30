package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
open class ServiceBusTest

fun main(args: Array<String>) {
    runApplication<ServiceBusTest>(*args)
}