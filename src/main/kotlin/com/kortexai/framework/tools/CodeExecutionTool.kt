package com.kortexai.framework.tools

import javax.script.ScriptEngineManager

class CodeExecutionTool : Tool {
    private val engine = ScriptEngineManager().getEngineByName("kotlin")

    override fun execute(input: String): String {
        return try {
            val result = engine.eval(input)
            "Resultado da execução do código: $result"
        } catch (e: Exception) {
            "Erro na execução do código: ${e.message}"
        }
    }
}