package com.kortexai.framework.agent.specialized

class ResearchAgent(
    name: String,
    llmClient: LLMClient,
    memory: Memory? = null
) : Agent(name, llmClient, memory) {
    
    override suspend fun performTask(task: String): String {
        val searchTool = ToolRegistry.getTool("web_search") as? WebSearchTool
        val searchResults = searchTool?.execute(task) ?: ""
        
        return super.performTask("Analise e sintetize: $searchResults\nTarefa original: $task")
    }
}
