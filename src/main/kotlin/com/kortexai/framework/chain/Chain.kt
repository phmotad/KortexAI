package com.kortexai.framework.chain

/**
 * Interface para encadeamentos de execução (chains).
 * Permite definir pipelines que combinem LLMs, ferramentas e memória.
 */
interface Chain {
    suspend fun run(prompt: String): String
}
