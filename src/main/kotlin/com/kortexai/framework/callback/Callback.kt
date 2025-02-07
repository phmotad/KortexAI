package com.kortexai.framework.callback

interface Callback {
    fun onTaskStart(task: Task)
    fun onTaskComplete(task: Task, result: String)
    fun onTaskError(task: Task, error: Throwable)
    fun onAgentAction(agent: Agent, action: String)
    fun onChainStart(chain: Chain)
    fun onChainEnd(chain: Chain, result: String)
}

class LoggingCallback : Callback {
    override fun onTaskStart(task: Task) {
        println("Task started: ${task.id} - ${task.description}")
    }
    
    override fun onTaskComplete(task: Task, result: String) {
        println("Task completed: ${task.id} - Result: $result")
    }
    
    override fun onTaskError(task: Task, error: Throwable) {
        println("Task error: ${task.id} - Error: ${error.message}")
    }
    
    override fun onAgentAction(agent: Agent, action: String) {
        println("Agent ${agent.name} performing: $action")
    }
    
    override fun onChainStart(chain: Chain) {
        println("Chain started: ${chain::class.simpleName}")
    }
    
    override fun onChainEnd(chain: Chain, result: String) {
        println("Chain completed: ${chain::class.simpleName} - Result: $result")
    }
}
