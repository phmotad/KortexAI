package com.kortexai.framework.tools

/**
 * Interface base para todas as ferramentas do framework KortexAI.
 * Define o contrato básico para execução de tarefas.
 */
interface Tool {
    /**
     * Executa uma tarefa específica da ferramenta.
     * @param input A entrada para a ferramenta processar
     * @return Resultado da execução
     */
    fun execute(input: String): String
}
