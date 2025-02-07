package com.kortexai.framework

import com.kortexai.framework.agent.Agent
import com.kortexai.framework.agent.DynamicAgent
import com.kortexai.framework.chain.LLMChain
import com.kortexai.framework.llm.OpenAIClient
import com.kortexai.framework.memory.LocalMemory
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Configuração do cliente LLM
    val openAIClient = OpenAIClient("sua-chave-api-aqui")
    
    // Configuração da memória
    val memory = LocalMemory()
    
    // Criação do agente (DynamicAgent é um Agent)
    val agent: Agent = DynamicAgent("Agente Dinâmico", openAIClient, memory)
    
    // Criação de uma chain simples
    val chain = LLMChain(openAIClient)
    
    // Criação do executor que espera um Agent
    val executor = Executor(agent, chain, memory)
    
    // Execução de uma tarefa
    val prompt = "Explique como funciona o Kotlin Multiplatform"
    val resultado = executor.execute(prompt)
    println("Resultado:\n$resultado")
}
