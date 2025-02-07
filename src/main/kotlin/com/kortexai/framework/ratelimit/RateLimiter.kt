package com.kortexai.framework.ratelimit

import kotlinx.coroutines.delay
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

class RateLimiter(
    private val maxRequests: Int,
    private val duration: Duration
) {
    private val requestCounts = ConcurrentHashMap<String, MutableList<Long>>()

    suspend fun checkAndWait(key: String) {
        val now = System.currentTimeMillis()
        val windowStart = now - duration.toMillis()
        
        val requests = requestCounts.getOrPut(key) { mutableListOf() }
        requests.removeAll { it < windowStart }
        
        if (requests.size >= maxRequests) {
            val oldestRequest = requests.first()
            val waitTime = (oldestRequest + duration.toMillis()) - now
            if (waitTime > 0) {
                delay(waitTime)
            }
            requests.removeAt(0)
        }
        
        requests.add(now)
    }
}
