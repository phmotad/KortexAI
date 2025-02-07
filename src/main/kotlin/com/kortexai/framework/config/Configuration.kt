package com.kortexai.framework.config

object Configuration {
    private val settings = mutableMapOf<String, Any>()

    fun set(key: String, value: Any) {
        settings[key] = value
    }

    fun <T> get(key: String, default: T): T {
        @Suppress("UNCHECKED_CAST")
        return settings[key] as? T ?: default
    }

    fun load(filePath: String) {
        // Implementar carregamento de configuração de arquivo
    }
}
