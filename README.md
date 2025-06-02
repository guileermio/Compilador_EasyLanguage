# Compilador EasyLanguage

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![ANTLR](https://img.shields.io/badge/ANTLR-4.x-blue.svg)](https://www.antlr.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Uma plataforma completa para transformar e validar seu código EasyLanguage. Executa detecção de tokens na fase léxica, verifica regras gramaticais na análise sintática e confere coerência de tipos e escopos na etapa semântica. Gere um código Java otimizado e acompanhe, em tempo real, a tabela de símbolos do seu programa.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Características da Linguagem](#características-da-linguagem)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Exemplos de Código](#exemplos-de-código)
- [API e Componentes](#api-e-componentes)
- [Testes](#testes)
- [Contribuição](#contribuição)
- [Roadmap](#roadmap)
- [Licença](#licença)

## 🎯 Sobre o Projeto

O **Compilador EasyLanguage** é uma implementação educacional completa de um compilador para a linguagem de programação EduScript (renomeada para EasyLanguage). Este projeto foi desenvolvido como parte da disciplina de Compiladores do curso de Engenharia da Computação da Faculdade Engenheiro Salvador Arena.

### Objetivos

- **Experimentação Prática**: Aplicação dos principais conceitos da disciplina de compiladores
- **Especificação Completa**: Desenvolvimento de uma linguagem educacional robusta
- **Competências Técnicas**: Uso de ferramentas modernas de engenharia de software
- **Interface Amigável**: IDE web com destaque de sintaxe e análise em tempo real

## 🚀 Características da Linguagem

### Sintaxe Clara e Intuitiva
- Palavras-chave em português para facilitar o aprendizado
- Sintaxe inspirada em Python e Pascal
- Estrutura de código limpa e legível

### Sistema de Tipos
- **Tipagem Estática**: Todas as variáveis devem ter tipos declarados
- **Detecção de Erros**: Verificação em tempo de compilação
- **Tipos Suportados**: `inteiro`, `real`, `logico`, `texto`

### Estruturas de Controle
- **Condicionais**: `se-senao-fimse`
- **Loops**: `enquanto-fimenquanto`, `para-fimpara`
- **Estruturas de Dados**: Arrays unidimensionais e bidimensionais

### Funcionalidades Avançadas
- **Funções e Procedimentos**: Com passagem por valor e referência
- **Sistema de Módulos**: Importação de código de outros arquivos
- **Operações I/O**: `ler()` e `escrever()` para interação com usuário

## 🏗️ Arquitetura

O compilador segue a arquitetura clássica de compiladores com as seguintes fases:

```
Código Fonte → Análise Léxica → Análise Sintática → Análise Semântica → Geração de Código
```

### Componentes Principais

1. **Frontend Web**: Interface de usuário com editor de código
2. **Analisador Léxico**: Tokenização usando ANTLR4
3. **Analisador Sintático**: Construção da AST
4. **Analisador Semântico**: Verificação de tipos e escopo
5. **Gerador de Código**: Compilação para Java bytecode
6. **Tabela de Símbolos**: Gerenciamento de escopo e identificadores

## 📋 Pré-requisitos

- **Java**: JDK 11 ou superior
- **Maven**: 3.6 ou superior
- **ANTLR**: 4.x (incluído como dependência)
- **Navegador Web**: Para interface gráfica

## 🔧 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/compilador-easylanguage.git
cd compilador-easylanguage
```

### 2. Compile o Projeto

```bash
mvn clean compile
```

### 3. Execute a Aplicação

```bash
mvn exec:java -Dexec.mainClass="br.edu.cefsa.EasyLanguageIdeApplication"
```

### 4. Acesse a Interface

Abra seu navegador e acesse: `http://localhost:8080`

## 💻 Uso

### Interface Web

1. **Editor de Código**: Digite seu código EasyLanguage no editor principal
2. **Compilar**: Clique no botão "Compilar" para processar o código
3. **Resultados**: Visualize os resultados nas abas:
   - **Saída**: Resultado da execução
   - **Java Gerado**: Código Java compilado
   - **Símbolos**: Tabela de símbolos do programa

### Linha de Comando

```bash
java -jar target/easylanguage-compiler.jar arquivo.easy
```

### Opções de Compilação

```bash
# Apenas análise léxica
java -jar easylanguage-compiler.jar -lexer arquivo.easy

# Apenas análise sintática  
java -jar easylanguage-compiler.jar -parser arquivo.easy

# Análise semântica completa
java -jar easylanguage-compiler.jar -semantic arquivo.easy

# Gerar código Java
java -jar easylanguage-compiler.jar -codegen arquivo.easy
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/edu/cefsa/
│   │   ├── antlr4/                 # Classes geradas pelo ANTLR
│   │   ├── compiler/               # Core do compilador
│   │   │   ├── abstractsyntaxtree/ # AST e visitadores
│   │   │   ├── datastructures/     # Estruturas de dados
│   │   │   ├── exceptions/         # Exceções customizadas
│   │   │   ├── main/              # Ponto de entrada
│   │   │   ├── parser/            # Analisadores
│   │   │   └── semantic/          # Análise semântica
│   │   ├── controller/            # Controladores web
│   │   ├── ide/                   # Interface do IDE
│   │   ├── model/                 # Modelos de dados
│   │   └── service/               # Serviços de negócio
│   └── resources/
│       ├── static/                # Recursos estáticos (CSS, JS)
│       ├── templates/             # Templates Thymeleaf
│       └── EasyLanguage.g4        # Gramática ANTLR
└── test/                          # Testes unitários
```

## 📝 Exemplos de Código

### Programa Básico

```easylanguage
programa HelloWorld;

inicio
    escrever("Olá, Mundo!");
fimprograma
```

### Função Recursiva

```easylanguage
programa ExemploFatorial;

// Declaração de função para cálculo de fatorial
funcao fatorial(n: inteiro): inteiro
inicio
    se n <= 1 entao
        retornar 1;
    senao
        retornar n * fatorial(n - 1);
    fimse
fim

// Programa principal
inicio
    var numero: inteiro;
    var resultado: inteiro;
    
    escrever("Digite um número para calcular o fatorial: ");
    ler(numero);
    
    resultado = fatorial(numero);
    
    escrever("O fatorial de ", numero, " é ", resultado);
fimprograma
```

### Estruturas de Controle

```easylanguage
programa ExemploLoop;

inicio
    var i: inteiro;
    var soma: inteiro;
    
    soma = 0;
    
    para i = 1 ate 10 faca
        soma = soma + i;
    fimpara
    
    escrever("A soma dos números de 1 a 10 é: ", soma);
fimprograma
```

## 🔌 API e Componentes

### CompilerService

```java
@Service
public class CompilerService {
    public CompilationResult compile(String sourceCode)
    public LexicalAnalysisResult analyzeLexical(String sourceCode)
    public SyntaxAnalysisResult analyzeSyntax(String sourceCode)
    public SemanticAnalysisResult analyzeSemantic(String sourceCode)
}
```

### EasyLanguageController

```java
@RestController
@RequestMapping("/api/compiler")
public class EasyLanguageController {
    @PostMapping("/compile")
    public ResponseEntity<CompilationResult> compile(@RequestBody CompileRequest request)
    
    @GetMapping("/examples")
    public ResponseEntity<List<CodeExample>> getExamples()
}
```

### Analisador Semântico

```java
public class SemanticAnalyzer extends EasyLanguageBaseVisitor<Void> {
    private SymbolTable symbolTable;
    private List<SemanticError> errors;
    
    public void analyze(ParseTree tree)
    // Implementação da análise semântica
}
```

## 🧪 Testes

### Executar Todos os Testes

```bash
mvn test
```

### Testes por Categoria

```bash
# Testes de análise léxica
mvn test -Dtest=LexicalAnalyzerTest

# Testes de análise sintática
mvn test -Dtest=SyntaxAnalyzerTest

# Testes de análise semântica
mvn test -Dtest=SemanticAnalyzerTest
```

### Cobertura de Código

```bash
mvn jacoco:report
```

Os relatórios são gerados em `target/site/jacoco/index.html`

## 🤝 Contribuição

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Diretrizes de Contribuição

- Mantenha o código bem documentado
- Adicione testes para novas funcionalidades
- Siga as convenções de código Java
- Atualize a documentação quando necessário

## 🛣️ Roadmap

### Versão 2.0
- [ ] Geração de código para JVM
- [ ] Otimizações avançadas de código
- [ ] Depurador integrado na IDE
- [ ] Suporte à programação orientada a objetos

### Versão 2.5
- [ ] Biblioteca padrão com funções matemáticas
- [ ] Manipulação avançada de strings
- [ ] Sistema de módulos mais robusto
- [ ] IDE desktop com Electron

### Versão 3.0
- [ ] Compilação para outras linguagens (C++, Python)
- [ ] Análise estática avançada
- [ ] Profiler de performance
- [ ] Plugin para VS Code

## 📚 Referências

- **AHO, SETHI, Ravi; ULLMAN, Jeffrey D.; MONICA S. LAM**. Compiladores: princípios, técnicas e ferramentas. Pearson Addison Wesley, 2008.
- **Parr, T.** (2013). The Definitive ANTLR 4 Reference.
- [Documentação ANTLR](https://www.antlr.org)
- [Repositório de Gramáticas ANTLR](https://github.com/antlr/grammars-v4)

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👥 Equipe

- **Professor Orientador**: [Nome do Professor]
- **Desenvolvedor Principal**: [Seu Nome]
- **Instituição**: Faculdade Engenheiro Salvador Arena
- **Disciplina**: Compiladores - 1º Semestre/2025

## 📞 Contato

- **Email**: [seu-email@exemplo.com]
- **LinkedIn**: [seu-linkedin]
- **Projeto**: [Link do repositório]

---

⭐ **Se este projeto foi útil para você, considere dar uma estrela no repositório!**
