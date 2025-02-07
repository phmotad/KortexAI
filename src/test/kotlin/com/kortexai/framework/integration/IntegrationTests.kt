package com.kortexai.framework.integration

class IntegrationTests {
    @Test
    fun testFullWorkflow() = runBlocking {
        val agent = Agent("TestAgent", OpenAIClient("test-key"))
        val memory = AdvancedMemory()
        val workflow = Workflow(listOf(
            WorkflowStep("research", "Research about: {input}"),
            WorkflowStep("analyze", "Analyze findings: {input}"),
            WorkflowStep("summarize", "Summarize: {input}")
        ))
        
        val result = workflow.execute("Kotlin Multiplatform")
        assertNotNull(result)
    }
}
