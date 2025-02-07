package com.kortexai.framework.task

enum class TaskPriority {
    HIGH, MEDIUM, LOW
}

enum class TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED, FAILED
}

data class Task(
    val id: String,
    val description: String,
    val priority: TaskPriority,
    var status: TaskStatus = TaskStatus.PENDING,
    val dependencies: List<String> = emptyList(),
    var assignedTo: String? = null,
    var result: String? = null
)

class TaskManager {
    private val tasks = mutableMapOf<String, Task>()
    
    fun addTask(task: Task) {
        tasks[task.id] = task
    }
    
    fun getNextExecutableTask(): Task? {
        return tasks.values
            .filter { it.status == TaskStatus.PENDING }
            .filter { task -> task.dependencies.all { depId -> 
                tasks[depId]?.status == TaskStatus.COMPLETED 
            }}
            .maxByOrNull { it.priority }
    }
    
    fun updateTaskStatus(taskId: String, status: TaskStatus, result: String? = null) {
        tasks[taskId]?.let { task ->
            task.status = status
            task.result = result
        }
    }
}
