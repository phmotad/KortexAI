package com.kortexai.framework.memory

/**
 * Implementação de Memory que armazena dados em memória usando um MutableMap.
 */
class LocalMemory : Memory {
    private val memoryStore = mutableMapOf<String, String>()

    override fun save(key: String, value: String) {
        memoryStore[key] = value
    }

    override fun retrieve(key: String): String? {
        return memoryStore[key]
    }
}
