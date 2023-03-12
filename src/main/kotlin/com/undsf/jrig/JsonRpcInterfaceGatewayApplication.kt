package com.undsf.jrig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JsonRpcInterfaceGatewayApplication

fun main(args: Array<String>) {
	runApplication<JsonRpcInterfaceGatewayApplication>(*args)
}
