package com.kortexai.framework.consensus

class ConsensusSystem(private val agents: List<Agent>) {
    suspend fun reachConsensus(task: Task): String {
        // Cada agente avalia a tarefa
        val opinions = agents.map { agent ->
            agent.evaluate(task)
        }
        
        // Verifica se há consenso
        if (opinions.all { it == opinions.first() }) {
            return opinions.first()
        }
        
        // Se não houver consenso, inicia processo de debate
        return debateAndResolve(task, opinions)
    }
    
    private suspend fun debateAndResolve(task: Task, initialOpinions: List<String>): String {
        var currentOpinions = initialOpinions
        var rounds = 0
        val maxRounds = 3
        
        while (rounds < maxRounds && !hasConsensus(currentOpinions)) {
            currentOpinions = agents.map { agent ->
                agent.considerOpinions(task, currentOpinions)
            }
            rounds++
        }
        
        return if (hasConsensus(currentOpinions)) {
            currentOpinions.first()
        } else {
            // Se não houver consenso após máximo de rounds, usa votação
            majorityVote(currentOpinions)
        }
    }
    
    private fun hasConsensus(opinions: List<String>): Boolean =
        opinions.all { it == opinions.first() }
    
    private fun majorityVote(opinions: List<String>): String =
        opinions.groupBy { it }
            .maxByOrNull { it.value.size }
            ?.key ?: opinions.first()
}
