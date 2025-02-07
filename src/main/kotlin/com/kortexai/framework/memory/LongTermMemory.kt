package com.kortexai.framework.memory

import com.kortexai.framework.memory.Memory

/**
 * Implementação de Memory que armazena dados em longo prazo.
 * Utiliza um MutableMap para manter um histórico de interações.
 */
class LongTermMemory : Memory {
    private val memoryStore = mutableMapOf<String, MutableList<String>>()

    /**
     * Salva um valor associado a uma chave no armazenamento de longo prazo.
     * @param key Chave para identificação do valor
     * @param value Valor a ser armazenado
     */
    override fun save(key: String, value: String) {
        memoryStore.computeIfAbsent(key) { mutableListOf() }.add(value)
    }

    /**
     * Recupera todos os valores associados a uma chave do armazenamento de longo prazo.
     * @param key Chave para identificação do valor
     * @return Lista de valores armazenados ou uma mensagem se a chave não existir
     */
    override fun retrieve(key: String): String? {
        return memoryStore[key]?.joinToString(", ") ?: "Nenhum valor encontrado para a chave '$key'."
    }
}
