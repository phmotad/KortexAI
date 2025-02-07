package com.kortexai.framework.cache

class ResponseCache {
    private val cache = mutableMapOf<String, CachedResponse>()

    fun get(key: String): String? {
        val cached = cache[key]
        return if (cached?.isValid() == true) cached.response else null
    }

    fun put(key: String, response: String, ttlMinutes: Int = 60) {
        cache[key] = CachedResponse(
            response = response,
            expirationTime = System.currentTimeMillis() + (ttlMinutes * 60 * 1000)
        )
    }
}

data class CachedResponse(
    val response: String,
    val expirationTime: Long
) {
    fun isValid(): Boolean = System.currentTimeMillis() < expirationTime
}
