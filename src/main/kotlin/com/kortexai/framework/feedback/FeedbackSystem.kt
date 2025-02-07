package com.kortexai.framework.feedback

enum class FeedbackType {
    QUALITY,
    PERFORMANCE,
    ACCURACY,
    CREATIVITY
}

data class Feedback(
    val type: FeedbackType,
    val score: Double,
    val comments: String
)

class FeedbackSystem {
    private val feedbackStore = mutableMapOf<String, MutableList<Feedback>>()
    
    fun provideFeedback(agentId: String, feedback: Feedback) {
        feedbackStore.getOrPut(agentId) { mutableListOf() }.add(feedback)
    }
    
    fun getAgentScore(agentId: String, type: FeedbackType): Double {
        return feedbackStore[agentId]
            ?.filter { it.type == type }
            ?.map { it.score }
            ?.average() ?: 0.0
    }
    
    fun getTopPerformers(type: FeedbackType, limit: Int = 5): List<Pair<String, Double>> {
        return feedbackStore.map { (agentId, feedbacks) ->
            agentId to (feedbacks.filter { it.type == type }
                .map { it.score }
                .average() ?: 0.0)
        }.sortedByDescending { it.second }
            .take(limit)
    }
}
