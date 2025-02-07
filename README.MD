# KortexAI

KortexAI é uma biblioteca em Kotlin para integração com modelos de linguagem (LLMs), gerenciamento de memória, execução de ferramentas e orquestração de agentes inteligentes. Inspirada em projetos como CrewAI e Langchain, essa biblioteca modular e extensível facilita a construção de aplicações que necessitam de fluxos inteligentes e adaptativos, integrando APIs reais de LLMs e oferecendo um ecossistema completo para processamento de linguagem natural.

## Funcionalidades

- **Integração com LLMs**: Suporte a múltiplos provedores, como OpenAI, Ollama, Claude, Cohere, HuggingFace e DeepSeek, com chamadas assíncronas e tratamento robusto de erros.
- **Encadeamentos (Chains)**: Criação de pipelines customizáveis que combinam chamadas a LLM, ferramentas e gerenciamento de contexto.
- **Gerenciamento de Memória**: Armazenamento local, em longa duração ou via API REST, além de suporte ao histórico de conversação.
- **Agentes Inteligentes**: Implementação de agentes dinâmicos que analisam o input do usuário e decidem entre invocar um LLM diretamente ou acionar ferramentas registradas.
- **Registro e Execução de Ferramentas (Tools)**: Módulo de ToolRegistry para gerenciar ferramentas customizadas (como busca e execução de código) que podem ser integradas dinamicamente aos agentes.
- **Executor Orquestrador**: Componente que integra agentes, chains e memória, permitindo a execução de fluxos completos e customizáveis.
- **Suporte a Assincronismo**: Utilização de corrotinas do Kotlin para garantir alta performance e escalabilidade em chamadas assíncronas.

## Estrutura do Projeto

```
src/
├── main/
│   ├── kotlin/
│   │   ├── com/
│   │   │   ├── kortexai/
│   │   │   │   ├── framework/
│   │   │   │   │   ├── llm/          # Módulo de integração com LLMs
│   │   │   │   │   ├── memory/       # Módulo de gerenciamento de memória
│   │   │   │   │   ├── agent/        # Implementação de agentes
│   │   │   │   │   ├── crew/         # Orquestração de equipes
│   │   │   │   │   └── Main.kt       # Exemplo de uso
```

## Requisitos

- Java 17+
- Kotlin 1.9.0+
- Dependências:
  - Ktor (client-core, client-cio, client-content-negotiation, ktor-serialization-kotlinx-json)
  - kotlinx-coroutines-core
  - kotlinx-serialization-json
  - SLF4J (para logging)
  - JUnit Platform (para testes)

## Instalação e Configuração

1. **Clone o Repositório:**

   ```bash
   git clone <URL_DO_REPOSITÓRIO>
   cd <NOME_DO_DIRETÓRIO>
```

2. **Configure as Dependências e Build:**

Utilize o Gradle para construir o projeto:

./gradlew build

Configuração de API Keys:

3. **Para integrar com LLMs reais (como OpenAI), substitua a chave de API pela sua chave válida no arquivo de configuração ou diretamente na instância do cliente. Exemplo:**
val openAIClient = OpenAIClient("sua-chave-api-aqui")


## Exemplo de Uso

O exemplo abaixo demonstra como configurar os módulos da biblioteca e executar um fluxo completo com agente dinâmico, chain e gerenciamento de memória:

```kotlin
  import com.kortexai.framework.Executor
  import com.kortexai.framework.agent.DynamicAgent
  import com.kortexai.framework.chain.LLMChain
  import com.kortexai.framework.llm.OpenAIClient
  import com.kortexai.framework.memory.LocalMemory
  import com.kortexai.framework.tools.CodeExecutionTool
  import com.kortexai.framework.tools.SearchTool
  import com.kortexai.framework.tools.ToolRegistry
  import kotlinx.coroutines.runBlocking

  fun main() = runBlocking {
      // Configuração do cliente LLM (por exemplo, OpenAI)
      val openAIClient = OpenAIClient("sua-chave-api-aqui")

      // Configuração da memória (local ou outra implementação)
      val memory = LocalMemory()

      // Registro de ferramentas no ToolRegistry
      ToolRegistry.registerTool("buscar", SearchTool())
      ToolRegistry.registerTool("executar código", CodeExecutionTool())

      // Criação do agente dinâmico, que decide entre LLM ou ferramenta
      val dynamicAgent = DynamicAgent("Agente Dinâmico", openAIClient, memory)

      // Criação de uma Chain simples utilizando o OpenAI
      val llmChain = LLMChain(openAIClient)

      // Criação do Executor que orquestra a execução do fluxo completo
      val executor = Executor(dynamicAgent, llmChain, memory)

      // Execução do fluxo com um prompt enriquecido com histórico de conversa
      val prompt = "Explique como funciona o Kotlin Multiplatform."
      val resultado = executor.execute(prompt)

      println("Resultado final: \n$resultado")
  }

Componentes Principais
1. Clientes de LLM (llm)
Contém implementações para integração com diversos provedores de LLM. Cada implementação usa corrotinas e Ktor para chamadas assíncronas e tratamento de erros.
Arquivo exemplo: OpenAIClient.kt

2. Encadeamentos (Chains)
Definem pipelines de execução que podem combinar múltiplos passos, como pré-processamento, chamadas a LLM e execução de ferramentas.
Arquivos: Chain.kt, LLMChain.kt

3. Gerenciamento de Memória (memory)
Oferece diferentes estratégias para armazenamento de contexto e histórico de diálogos. Possui implementações locais, via API e de longo prazo.
Arquivos: Memory.kt, LocalMemory.kt, APIMemory.kt, LongTermMemory.kt

4. Agentes Inteligentes (agent)
Agentes que encapsulam a lógica de execução. O DynamicAgent analisa o prompt e decide se invoca uma ferramenta ou chama diretamente o LLM.
Arquivos: Agent.kt, DynamicAgent.kt

5. Ferramentas e Registro (tools)
Ferramentas utilitárias para execução de código, buscas e outras ações customizadas. Possui um ToolRegistry para gerenciamento dinâmico de ferramentas disponíveis.
Arquivos: Tool.kt, SearchTool.kt, CodeExecutionTool.kt, ToolRegistry.kt

6. Executor Orquestrador
Integra os módulos de agente, chain e memória em um pipeline completo que gerencia o fluxo de execução e histórico de conversação.
Arquivo: Executor.kt

Testes e Contribuições
Testes: É recomendado que os usuários escrevam testes unitários e de integração para validar os fluxos e a integração entre os módulos. Utilize JUnit e corrotinas para testes assíncronos.
Contribuições: Sinta-se à vontade para contribuir com a biblioteca. Crie issues e pull requests no repositório oficial para reportar bugs, sugerir melhorias ou implementar novas funcionalidades.
Licença
MIT License

Contato

Para dúvidas, sugestões ou contribuições, entre em contato através do repositório oficial ou via canais de comunicação disponíveis.

Esta documentação visa fornecer uma visão completa da biblioteca KortexAI, permitindo que desenvolvedores implementem e integrem os principais módulos para construir aplicações robustas com agentes inteligentes e fluxos adaptativos.