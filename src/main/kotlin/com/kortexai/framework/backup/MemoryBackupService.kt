package com.kortexai.framework.backup

class MemoryBackupService(private val memory: Memory) {
    fun backup(path: String) {
        val backupData = Json.encodeToString(memory.dump())
        File(path).writeText(backupData)
    }
    
    fun restore(path: String) {
        val backupData = File(path).readText()
        memory.restore(Json.decodeFromString(backupData))
    }
    
    fun scheduleBackup(interval: Duration) {
        GlobalScope.launch {
            while (true) {
                backup("backup_${System.currentTimeMillis()}.json")
                delay(interval.toMillis())
            }
        }
    }
}
