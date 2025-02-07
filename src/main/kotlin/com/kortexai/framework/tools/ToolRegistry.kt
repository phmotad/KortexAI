package com.kortexai.framework.tools

object ToolRegistry {
    private val tools = mutableMapOf<String, Tool>()

    val registeredTools: Map<String, Tool>
        get() = tools

    fun registerTool(keyword: String, tool: Tool) {
        tools[keyword] = tool
    }

    fun getTool(keyword: String): Tool? = tools[keyword]
}
