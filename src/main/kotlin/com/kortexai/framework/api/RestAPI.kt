package com.kortexai.framework.api

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*

class RestAPI {
    fun start() {
        embeddedServer(Netty, port = 8080) {
            routing {
                get("/agents") {
                    call.respond(AgentRegistry.getAllAgents())
                }
                
                post("/tasks") {
                    val task = call.receive<Task>()
                    call.respond(TaskExecutor.execute(task))
                }
                
                get("/metrics") {
                    call.respond(MetricsCollector.getAllMetrics())
                }
            }
        }.start(wait = true)
    }
}
