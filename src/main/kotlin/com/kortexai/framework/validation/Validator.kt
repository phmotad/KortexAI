package com.kortexai.framework.validation

interface ResponseValidator {
    fun validate(response: String): ValidationResult
}

data class ValidationResult(
    val isValid: Boolean,
    val score: Double,
    val feedback: String
)

class LLMValidator(private val llmClient: LLMClient) : ResponseValidator {
    override fun validate(response: String): ValidationResult {
        // Implementar validação usando LLM
        return ValidationResult(true, 0.9, "Resposta válida")
    }
}
