package com.kortexai.framework.tools.specialized

class WebSearchTool : Tool {
    override val name = "web_search"
    override val description = "Realiza buscas na web"

    override suspend fun execute(input: String): String {
        // Implementar integração com motor de busca
        return "Resultados da busca para: $input"
    }
}
