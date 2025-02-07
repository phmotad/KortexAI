package com.kortexai.framework.chain

interface Chain {
    suspend fun process(input: String): String
}

class SequentialChain(private val chains: List<Chain>) : Chain {
    override suspend fun process(input: String): String {
        var currentInput = input
        chains.forEach { chain ->
            currentInput = chain.process(currentInput)
        }
        return currentInput
    }
}

class MapReduceChain(
    private val mapChains: List<Chain>,
    private val reduceChain: Chain
) : Chain {
    override suspend fun process(input: String): String {
        val mappedResults = mapChains.map { it.process(input) }
        return reduceChain.process(mappedResults.joinToString("\n"))
    }
}
