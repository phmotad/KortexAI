package com.kortexai.framework.tools

object ToolRegistry {
    private val tools = mutableMapOf<String, Tool>()
    private val toolCategories = mutableMapOf<String, MutableSet<Tool>>()

    fun registerTool(tool: Tool, category: String = "general") {
        tools[tool.name] = tool
        toolCategories.getOrPut(category) { mutableSetOf() }.add(tool)
    }

    fun getTool(name: String): Tool? = tools[name]
    
    fun getToolsByCategory(category: String): Set<Tool> = 
        toolCategories[category]?.toSet() ?: emptySet()
}
