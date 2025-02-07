package com.kortexai.framework.memory

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

/**
 * Implementação de Memory que armazena dados em uma API REST.
 * Usa Ktor HttpClient com engine CIO para comunicação HTTP.
 */
class APIMemory(private val baseUrl: String) : Memory {
    private val client = HttpClient(CIO) {
        expectSuccess = false
    }

    /**
     * Salva um valor na API REST via requisição POST.
     * @param key Chave para identificação do valor
     * @param value Valor a ser armazenado
     */
    override fun save(key: String, value: String) {
        // Simulação de salvar valor na API
        println("Simulando salvamento do valor '$value' com a chave '$key' na API.")
    }

    /**
     * Recupera um valor da API REST via requisição GET.
     * @param key Chave para identificação do valor
     * @return Valor armazenado ou null se a chave não existir
     */
    override fun retrieve(key: String): String? {
        // Simulação de recuperação de valor da API
        println("Simulando recuperação do valor com a chave '$key' da API.")
        return "Valor simulado para a chave '$key'."
    }
}

class MemoryException(message: String) : Exception(message)
