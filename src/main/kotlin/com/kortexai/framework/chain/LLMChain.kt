package com.kortexai.framework.chain

import com.kortexai.framework.llm.LLMClient

class LLMChain(private val llmClient: LLMClient) {
    suspend fun process(prompt: String): String {
        return llmClient.generateResponse(prompt)
    }
}
