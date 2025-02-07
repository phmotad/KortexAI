package com.kortexai.framework.execution

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

interface ExecutionStrategy {
    suspend fun execute(tasks: List<Task>): List<String>
}

class SequentialStrategy : ExecutionStrategy {
    override suspend fun execute(tasks: List<Task>): List<String> {
        return tasks.map { task ->
            task.execute()
        }
    }
}

class ParallelStrategy : ExecutionStrategy {
    override suspend fun execute(tasks: List<Task>): List<String> = coroutineScope {
        tasks.map { task ->
            async { task.execute() }
        }.map { it.await() }
    }
}

class HierarchicalStrategy(
    private val manager: Agent,
    private val specialists: List<Agent>
) : ExecutionStrategy {
    override suspend fun execute(tasks: List<Task>): List<String> {
        val delegatedTasks = manager.delegateTasks(tasks, specialists)
        return ParallelStrategy().execute(delegatedTasks)
    }
}
