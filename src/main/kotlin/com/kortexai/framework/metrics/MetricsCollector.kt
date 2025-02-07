package com.kortexai.framework.metrics

object MetricsCollector {
    private val metrics = mutableMapOf<String, MutableList<Double>>()

    fun recordMetric(name: String, value: Double) {
        metrics.getOrPut(name) { mutableListOf() }.add(value)
    }

    fun getMetricAverage(name: String): Double =
        metrics[name]?.average() ?: 0.0

    fun getMetricHistory(name: String): List<Double> =
        metrics[name]?.toList() ?: emptyList()
}
