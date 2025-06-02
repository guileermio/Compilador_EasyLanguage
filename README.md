# 🚀 EasyLanguage Compiler – Complete Educational Compiler Platform

<div align="center">
  <img alt="Java Version" src="https://img.shields.io/badge/Java-17-orange?logo=java&style=for-the-badge" />
  <img alt="Maven Version" src="https://img.shields.io/badge/Maven-3.9+-blue?logo=apache-maven&style=for-the-badge" />
  <img alt="ANTLR Version" src="https://img.shields.io/badge/ANTLR-4.x-green?logo=antlr&style=for-the-badge" />
  <img alt="License" src="https://img.shields.io/badge/License-BSD_3--Clause-blue?style=for-the-badge" />
</div>

## 🗂️ Table of Contents

- [⭐ Features](#-features)
- [🏗️ Technical Implementation](#️-technical-implementation)
- [⚙️ Technologies](#️-technologies)
- [🚀 Getting Started](#-getting-started)
- [💻 Usage Examples](#-usage-examples)
- [📜 License](#-license)
- [👥 Authors](#-authors)

---

## ⭐ Features

**A complete platform for transforming and validating EasyLanguage code:**

- ✅ **Multi-Phase Compilation**

  - Token detection in lexical phase using ANTLR4
  - Grammar rule verification in syntactic analysis
  - Type and scope coherence checking in semantic phase
  - Optimized Java code generation

- 🌐 **Real-time Web IDE**

  - Syntax highlighting and live analysis
  - Interactive symbol table visualization
  - Integrated compilation results display
  - Modern responsive interface

- 🧠 **Advanced Language Features**

  - Static typing with compile-time error detection
  - Supported types: `inteiro` (integer), `real` (float), `logico` (boolean), `texto` (string)
  - Control structures: `se-senao-fimse` (if-else), loops, arrays
  - Functions and procedures with parameter passing

- 🔍 **Comprehensive Analysis**

  - Lexical analysis with detailed tokenization
  - Syntax tree construction and validation
  - Semantic analysis with type checking and scope management
  - Real-time symbol table tracking

- 📚 **Educational Focus**
  - Portuguese keywords for easier learning
  - Clean, readable syntax inspired by Python and Pascal
  - Complete documentation and examples
  - Academic-oriented development approach

---

## 🏗️ Technical Implementation

### Compiler Architecture

The compiler follows the classic compiler architecture with the following phases:

```
Source Code → Lexical Analysis → Syntax Analysis → Semantic Analysis → Code Generation
```

### Language Grammar Specification

EasyLanguage uses a context-free grammar with Portuguese keywords:

```bnf
programa → 'programa' ID ';' declaracoes 'inicio' comandos 'fimprograma'
declaracoes → declaracao declaracoes | ε
declaracao → 'var' ID ':' tipo ';' | funcao | procedimento
tipo → 'inteiro' | 'real' | 'logico' | 'texto'
comandos → comando comandos | ε
comando → atribuicao | condicional | repeticao | chamada_funcao
```

### Project Structure & Component Roles

```bash
.
├── AUTHORS                                   # Project contributors
├── LICENSE                                   # MIT license file
├── Manual do Usuário - EasyLanguage.md      # User manual in Portuguese
├── README.md                                 # Project documentation
├── mvnw                                      # Maven wrapper (Unix)
├── mvnw.cmd                                  # Maven wrapper (Windows)
├── pom.xml                                   # Maven project configuration
├── settings.xml                              # Maven settings
├── resources/                                # External resources
│   ├── EasyLanguage.g4                       # ANTLR grammar definition
│   ├── MainClass.java                        # Alternative main class
│   ├── antlr-4.12.0-complete.jar           # ANTLR runtime JAR
│   ├── antlr4-4.12.0-sources.jar           # ANTLR sources
│   ├── antlr4-4.12.0.jar                   # ANTLR library
│   ├── input.easy                           # Sample EasyLanguage program
│   └── inputEdu.easy                        # Educational sample program
├── src/
│   ├── br/edu/cefsa/compiler/parser/        # Generated ANTLR classes
│   │   ├── EasyLanguage.interp              # ANTLR interpreter data
│   │   ├── EasyLanguage.tokens              # Token definitions
│   │   ├── EasyLanguageBaseListener.java    # Base listener pattern
│   │   ├── EasyLanguageLexer.java           # Generated lexer
│   │   ├── EasyLanguageListener.java        # Parse tree listener
│   │   └── EasyLanguageParser.java          # Generated parser
│   └── main/
│       ├── java/br/edu/cefsa/
│       │   ├── EasyLanguageIdeApplication.java # Spring Boot application
│       │   ├── antlr4/                       # ANTLR resources
│       │   │   ├── EduScript.g4              # Alternative grammar
│       │   │   └── exemplo.eds               # EduScript example
│       │   ├── compiler/                     # Compiler core components
│       │   │   ├── abstractsyntaxtree/       # AST node implementations
│       │   │   │   ├── AbstractCommand.java  # Base command class
│       │   │   │   ├── CommandAtribuicao.java # Assignment commands
│       │   │   │   ├── CommandDecisao.java   # Decision structures
│       │   │   │   ├── CommandEscrita.java   # Write/output commands
│       │   │   │   ├── CommandFor.java       # For loop commands
│       │   │   │   ├── CommandLeitura.java   # Read/input commands
│       │   │   │   ├── CommandRepeticao.java # While loop commands
│       │   │   │   └── EasyProgram.java      # Program root node
│       │   │   ├── datastructures/           # Symbol management
│       │   │   │   ├── EasyFunction.java     # Function representations
│       │   │   │   ├── EasySymbol.java       # Symbol base class
│       │   │   │   ├── EasySymbolTable.java  # Symbol table implementation
│       │   │   │   └── EasyVariable.java     # Variable representations
│       │   │   ├── exceptions/               # Custom exceptions
│       │   │   │   └── EasySemanticException.java # Semantic error handling
│       │   │   ├── main/                     # Main entry points
│       │   │   │   ├── EasyLanguageCompiler.java # Primary compiler class
│       │   │   │   └── MainClass.java        # Alternative main class
│       │   │   ├── parser/                   # Parser components
│       │   │   │   ├── EasyLanguageBaseListener.java # Listener implementation
│       │   │   │   ├── EasyLanguageLexer.java # Lexical analyzer
│       │   │   │   ├── EasyLanguageListener.java # Parse tree listener
│       │   │   │   ├── EasyLanguageParser.java # Syntax analyzer
│       │   │   │   └── main.java             # Parser main class
│       │   │   └── semantic/                 # Semantic analysis
│       │   │       ├── EasySemanticAnalyzer.java # Type and scope checking
│       │   │       └── EasySemanticError.java # Semantic error representation
│       │   ├── controller/                   # Web API controllers
│       │   │   ├── CompilerController.java   # Compilation endpoints
│       │   │   ├── EasyLanguageController.java # Language-specific API
│       │   │   └── IDEController.java        # IDE interface controller
│       │   ├── ide/                          # IDE components
│       │   │   └── EduScriptIDE.java         # EduScript IDE implementation
│       │   ├── model/                        # Data models
│       │   │   └── CompilationResult.java    # Compilation result wrapper
│       │   └── service/                      # Business logic services
│       │       ├── CompilerService.java      # Core compilation service
│       │       └── ExampleService.java       # Code example management
│       └── resources/                        # Application resources
│           ├── application.properties        # Spring Boot configuration
│           ├── static/js/                    # JavaScript resources
│           │   └── easylanguage-mode.js      # Syntax highlighting mode
│           └── templates/                    # Thymeleaf templates
│               └── ide.html                  # Web IDE interface
└── target/generated-sources/annotations/     # Maven generated sources
```

---

## ⚙️ Technologies

- **Backend**: Spring Boot 3.x + Java 17+
- **Frontend**: Thymeleaf + JavaScript
- **Parser Generator**: ANTLR 4.x
- **Build Tool**: Maven 3.6+
- **Target Platform**: JVM (Java bytecode generation)

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- Modern web browser for IDE interface

### Installation

Clone the repository and build the project:

```bash
git clone git@github.com:guileermio/Compilador_EasyLanguage.git
cd Compilador_EasyLanguage
mvn clean compile
```

### Running the Application

Start the web IDE:

```bash
mvn spring-boot:run
```

Access the IDE at: 🌐 [http://localhost:8080](http://localhost:8080)

---

## 💻 Usage Examples

### Basic Program Structure

```easylanguage
programa HelloWorld;

inicio
    escrever("Hello, World!");
fimprograma
```

### Recursive Function Implementation

```easylanguage
programa FactorialExample;

// Function declaration for factorial calculation
funcao fatorial(n: inteiro): inteiro
inicio
    se n <= 1 entao
        retornar 1;
    senao
        retornar n * fatorial(n - 1);
    fimse
fim

// Main program
inicio
    var numero: inteiro;
    var resultado: inteiro;

    escrever("Enter a number to calculate factorial: ");
    ler(numero);

    resultado = fatorial(numero);

    escrever("The factorial of ", numero, " is ", resultado);
fimprograma
```

### Control Structures and Loops

```easylanguage
programa LoopExample;

inicio
    var i: inteiro;
    var soma: inteiro;

    soma = 0;

    para i = 1 ate 10 faca
        soma = soma + i;
    fimpara

    escrever("Sum of numbers from 1 to 10 is: ", soma);
fimprograma
```

### Array Manipulation

```easylanguage
programa ArrayExample;

inicio
    var numeros: vetor[10] de inteiro;
    var i: inteiro;
    var maior: inteiro;

    // Input array elements
    para i = 0 ate 9 faca
        escrever("Enter number ", i + 1, ": ");
        ler(numeros[i]);
    fimpara

    // Find maximum value
    maior = numeros[0];
    para i = 1 ate 9 faca
        se numeros[i] > maior entao
            maior = numeros[i];
        fimse
    fimpara

    escrever("Maximum value is: ", maior);
fimprograma
```

---

## 📜 License

Distributed under the **[BSD 3-Clause License](./LICENSE)**.

---

## 👥 Authors

Students from **Engenheiro Salvador Arena College**:
➡️ [Complete Contributors List](./AUTHORS)

---

<div align="center">
  <sub>Built with ♥ by Computer Engineering students</sub><br>
  <sub>Compilers Course Project • 2025 Semester</sub>
</div>
