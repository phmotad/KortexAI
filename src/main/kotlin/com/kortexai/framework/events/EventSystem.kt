package com.kortexai.framework.events

object EventSystem {
    private val listeners = mutableMapOf<EventType, MutableSet<EventListener>>()

    fun addEventListener(type: EventType, listener: EventListener) {
        listeners.getOrPut(type) { mutableSetOf() }.add(listener)
    }

    fun emit(event: Event) {
        listeners[event.type]?.forEach { it.onEvent(event) }
    }
}

enum class EventType {
    TASK_STARTED,
    TASK_COMPLETED,
    AGENT_ACTION,
    ERROR
}

interface EventListener {
    fun onEvent(event: Event)
}

data class Event(
    val type: EventType,
    val data: Map<String, Any>
)
