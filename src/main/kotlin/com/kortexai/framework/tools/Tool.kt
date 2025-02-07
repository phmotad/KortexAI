package com.kortexai.framework.tools

/**
 * Interface que define o contrato para ferramentas.
 */
interface Tool {
    /**
     * Executa uma ação com a entrada fornecida.
     * @param input A entrada para a ferramenta
     * @return O resultado da execução
     */
    fun execute(input: String): String
}
