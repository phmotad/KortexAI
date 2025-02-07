package com.kortexai.framework.memory

/**
 * Interface para gerenciamento de memória.
 */
interface Memory {
    fun save(key: String, value: String)
    fun retrieve(key: String): String?
}
