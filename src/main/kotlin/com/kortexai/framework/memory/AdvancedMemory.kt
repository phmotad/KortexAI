package com.kortexai.framework.memory

class AdvancedMemory : Memory {
    private val shortTermMemory = mutableMapOf<String, String>()
    private val longTermMemory = mutableMapOf<String, List<String>>()
    private val workingMemory = mutableMapOf<String, Any>()

    override fun save(key: String, value: String) {
        shortTermMemory[key] = value
        longTermMemory.getOrPut(key) { mutableListOf() }.plus(value)
    }

    override fun retrieve(key: String): String? {
        return shortTermMemory[key] ?: longTermMemory[key]?.lastOrNull()
    }

    fun getHistory(key: String): List<String> = longTermMemory[key] ?: emptyList()
}
