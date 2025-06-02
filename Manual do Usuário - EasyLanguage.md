# Manual do Usuário - EasyLanguage

## 📚 Guia Completo da Linguagem de Programação Educacional

### Versão 1.0 | Compilador EasyLanguage

---

## 📋 Índice

1. [Introdução](#introdução)
2. [Primeiros Passos](#primeiros-passos)
3. [Sintaxe Básica](#sintaxe-básica)
4. [Tipos de Dados](#tipos-de-dados)
5. [Variáveis e Constantes](#variáveis-e-constantes)
6. [Operadores](#operadores)
7. [Estruturas de Controle](#estruturas-de-controle)
8. [Funções e Procedimentos](#funções-e-procedimentos)
9. [Estruturas de Dados](#estruturas-de-dados)
10. [Entrada e Saída](#entrada-e-saída)
11. [Exemplos Práticos](#exemplos-práticos)
12. [Mensagens de Erro](#mensagens-de-erro)
13. [Dicas e Boas Práticas](#dicas-e-boas-práticas)
14. [FAQ](#faq)

---

## 🌟 Introdução

A **EasyLanguage** é uma linguagem de programação educacional desenvolvida especificamente para facilitar o aprendizado de programação. Com sintaxe clara em português e tipagem estática, ela oferece um ambiente seguro e intuitivo para iniciantes.

### Características Principais

- ✅ **Sintaxe em Português**: Palavras-chave familiares
- ✅ **Tipagem Estática**: Detecção de erros em tempo de compilação
- ✅ **Estruturas Fundamentais**: Variáveis, funções, loops e condicionais
- ✅ **Interface Web**: Editor online com destaque de sintaxe
- ✅ **Feedback Imediato**: Análise em tempo real do código

---

## 🚀 Primeiros Passos

### 1. Acessando o Compilador

1. Abra seu navegador web
2. Acesse `http://localhost:8080` (após iniciar a aplicação)
3. Você verá a interface do **Compilador EasyLanguage**

### 2. Seu Primeiro Programa

Digite o seguinte código no editor:

```easylanguage
programa MeuPrimeiroPrograma;

inicio
    escrever("Olá, mundo da programação!");
fimprograma
```

### 3. Compilando e Executando

1. Clique no botão **"Compilar"**
2. Visualize o resultado na aba **"Saída"**
3. Explore as abas **"Java Gerado"** e **"Símbolos"** para entender o processo

---

## 📝 Sintaxe Básica

### Estrutura de um Programa

Todo programa EasyLanguage segue esta estrutura básica:

```easylanguage
programa NomeDoPrograma;

// Declarações globais (opcional)
// Funções e procedimentos (opcional)

inicio
    // Código principal do programa
fimprograma
```

### Comentários

```easylanguage
// Comentário de linha única

/*
   Comentário de
   múltiplas linhas
*/
```

### Identificadores

- Devem começar com letra ou underscore (`_`)
- Podem conter letras, números e underscores
- Não podem ser palavras reservadas
- São **case-sensitive**

**Válidos**: `idade`, `nomeCompleto`, `_contador`, `valor1`
**Inválidos**: `1idade`, `nome-completo`, `se`, `para`

### Palavras Reservadas

```
programa, fimprograma, inicio, fim, se, senao, fimse, enquanto, 
fimenquanto, para, ate, faca, fimpara, funcao, procedimento, 
retornar, var, const, inteiro, real, logico, texto, verdadeiro, 
falso, ler, escrever, e, ou, nao
```

---

## 🏷️ Tipos de Dados

### Tipos Primitivos

| Tipo | Descrição | Exemplo |
|------|-----------|---------|
| `inteiro` | Números inteiros | `42`, `-15`, `0` |
| `real` | Números decimais | `3.14`, `-2.5`, `0.0` |
| `logico` | Valores lógicos | `verdadeiro`, `falso` |
| `texto` | Cadeias de caracteres | `"Olá"`, `"123"`, `""` |

### Literais

```easylanguage
// Inteiros
42
-15
0

// Reais
3.14
-2.5
0.0

// Lógicos
verdadeiro
falso

// Textos
"Olá, mundo!"
"O valor é: "
""  // string vazia
```

---

## 📊 Variáveis e Constantes

### Declaração de Variáveis

```easylanguage
programa ExemploVariaveis;

inicio
    var idade: inteiro;
    var nome: texto;
    var salario: real;
    var ativo: logico;
    
    // Declaração com inicialização
    var contador: inteiro = 0;
    var pi: real = 3.14159;
fimprograma
```

### Declaração de Constantes

```easylanguage
programa ExemploConstantes;

const PI: real = 3.14159;
const MAXIMO: inteiro = 100;
const TITULO: texto = "Sistema de Gestão";

inicio
    escrever("O valor de PI é: ", PI);
fimprograma
```

### Atribuição

```easylanguage
// Atribuição simples
idade = 25;
nome = "João Silva";
ativo = verdadeiro;

// Atribuição com expressões
total = preco * quantidade;
media = (nota1 + nota2 + nota3) / 3;
```

---

## ⚡ Operadores

### Operadores Aritméticos

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `+` | Adição | `5 + 3` resulta `8` |
| `-` | Subtração | `5 - 3` resulta `2` |
| `*` | Multiplicação | `5 * 3` resulta `15` |
| `/` | Divisão | `6 / 2` resulta `3` |
| `%` | Módulo (resto) | `7 % 3` resulta `1` |

### Operadores Relacionais

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `==` | Igual a | `5 == 5` resulta `verdadeiro` |
| `!=` | Diferente de | `5 != 3` resulta `verdadeiro` |
| `<` | Menor que | `3 < 5` resulta `verdadeiro` |
| `<=` | Menor ou igual | `5 <= 5` resulta `verdadeiro` |
| `>` | Maior que | `5 > 3` resulta `verdadeiro` |
| `>=` | Maior ou igual | `5 >= 5` resulta `verdadeiro` |

### Operadores Lógicos

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `e` | E lógico (AND) | `verdadeiro e falso` resulta `falso` |
| `ou` | OU lógico (OR) | `verdadeiro ou falso` resulta `verdadeiro` |
| `nao` | NÃO lógico (NOT) | `nao verdadeiro` resulta `falso` |

### Precedência de Operadores

1. `()` - Parênteses
2. `nao` - Negação lógica
3. `*`, `/`, `%` - Multiplicação, divisão, módulo
4. `+`, `-` - Adição, subtração
5. `<`, `<=`, `>`, `>=` - Relacionais
6. `==`, `!=` - Igualdade
7. `e` - E lógico
8. `ou` - OU lógico

---

## 🔄 Estruturas de Controle

### Estrutura Condicional (SE-SENAO)

#### Sintaxe Básica

```easylanguage
se (condicao) entao
    // comandos executados se condição for verdadeira
fimse
```

#### Com Alternativa

```easylanguage
se (condicao) entao
    // comandos para condição verdadeira
senao
    // comandos para condição falsa
fimse
```

#### Exemplo Prático

```easylanguage
programa ExemploCondicional;

inicio
    var idade: inteiro;
    
    escrever("Digite sua idade: ");
    ler(idade);
    
    se (idade >= 18) entao
        escrever("Você é maior de idade.");
    senao
        escrever("Você é menor de idade.");
    fimse
fimprograma
```

#### Condicionais Aninhadas

```easylanguage
se (nota >= 90) entao
    escrever("Excelente!");
senao
    se (nota >= 70) entao
        escrever("Bom!");
    senao
        se (nota >= 50) entao
            escrever("Regular");
        senao
            escrever("Insuficiente");
        fimse
    fimse
fimse
```

### Estrutura de Repetição (ENQUANTO)

#### Sintaxe

```easylanguage
enquanto (condicao) faca
    // comandos a serem repetidos
fimenquanto
```

#### Exemplo

```easylanguage
programa ExemploEnquanto;

inicio
    var contador: inteiro = 1;
    
    enquanto (contador <= 10) faca
        escrever("Contagem: ", contador);
        contador = contador + 1;
    fimenquanto
    
    escrever("Fim da contagem!");
fimprograma
```

### Estrutura de Repetição (PARA)

#### Sintaxe

```easylanguage
para variavel = valorInicial ate valorFinal faca
    // comandos a serem repetidos
fimpara
```

#### Exemplo

```easylanguage
programa ExemploPara;

inicio
    var i: inteiro;
    var soma: inteiro = 0;
    
    para i = 1 ate 100 faca
        soma = soma + i;
    fimpara
    
    escrever("A soma de 1 a 100 é: ", soma);
fimprograma
```

---

## 🔧 Funções e Procedimentos

### Declaração de Função

```easylanguage
funcao nomeDaFuncao(parametros): tipoRetorno
inicio
    // corpo da função
    retornar valor;
fim
```

### Exemplo de Função

```easylanguage
programa ExemploFuncao;

funcao calcularQuadrado(numero: inteiro): inteiro
inicio
    retornar numero * numero;
fim

funcao ehPar(numero: inteiro): logico
inicio
    retornar (numero % 2) == 0;
fim

inicio
    var valor: inteiro = 8;
    var quadrado: inteiro;
    
    quadrado = calcularQuadrado(valor);
    escrever("O quadrado de ", valor, " é ", quadrado);
    
    se (ehPar(valor)) entao
        escrever(valor, " é um número par.");
    senao
        escrever(valor, " é um número ímpar.");
    fimse
fimprograma
```

### Declaração de Procedimento

```easylanguage
procedimento nomeDoProc(parametros)
inicio
    // corpo do procedimento
fim
```

### Exemplo de Procedimento

```easylanguage
programa ExemploProc;

procedimento cumprimentar(nome: texto)
inicio
    escrever("Olá, ", nome, "!");
    escrever("Bem-vindo ao sistema!");
fim

procedimento mostrarInfo(nome: texto, idade: inteiro)
inicio
    escrever("Nome: ", nome);
    escrever("Idade: ", idade, " anos");
fim

inicio
    cumprimentar("Maria");
    mostrarInfo("João", 25);
fimprograma
```

### Recursão

```easylanguage
programa ExemploRecursao;

funcao fatorial(n: inteiro): inteiro
inicio
    se (n <= 1) entao
        retornar 1;
    senao
        retornar n * fatorial(n - 1);
    fimse
fim

funcao fibonacci(n: inteiro): inteiro
inicio
    se (n <= 1) entao
        retornar n;
    senao
        retornar fibonacci(n - 1) + fibonacci(n - 2);
    fimse
fim

inicio
    escrever("Fatorial de 5: ", fatorial(5));
    escrever("Fibonacci de 10: ", fibonacci(10));
fimprograma
```

---

## 📚 Estruturas de Dados

### Arrays Unidimensionais

#### Declaração

```easylanguage
var nomeArray: inteiro[tamanho];
var numeros: inteiro[10];
var nomes: texto[5];
```

#### Uso

```easylanguage
programa ExemploArray;

inicio
    var numeros: inteiro[5];
    var i: inteiro;
    
    // Preenchendo o array
    para i = 0 ate 4 faca
        numeros[i] = i * 2;
    fimpara
    
    // Exibindo o array
    para i = 0 ate 4 faca
        escrever("numeros[", i, "] = ", numeros[i]);
    fimpara
fimprograma
```

### Arrays Bidimensionais

#### Declaração

```easylanguage
var matriz: inteiro[linhas][colunas];
var tabela: inteiro[3][4];
```

#### Exemplo

```easylanguage
programa ExemploMatriz;

inicio
    var matriz: inteiro[3][3];
    var i, j: inteiro;
    
    // Preenchendo a matriz
    para i = 0 ate 2 faca
        para j = 0 ate 2 faca
            matriz[i][j] = i + j;
        fimpara
    fimpara
    
    // Exibindo a matriz
    para i = 0 ate 2 faca
        para j = 0 ate 2 faca
            escrever(matriz[i][j], " ");
        fimpara
        escrever(""); // nova linha
    fimpara
fimprograma
```

---

## 💬 Entrada e Saída

### Comando ESCREVER

#### Sintaxe

```easylanguage
escrever(expressao1, expressao2, ...);
```

#### Exemplos

```easylanguage
escrever("Olá mundo!");
escrever("A idade é: ", idade);
escrever("Soma: ", a, " + ", b, " = ", a + b);
```

### Comando LER

#### Sintaxe

```easylanguage
ler(variavel);
```

#### Exemplos

```easylanguage
programa ExemploEntradaSaida;

inicio
    var nome: texto;
    var idade: inteiro;
    var altura: real;
    
    escrever("Digite seu nome: ");
    ler(nome);
    
    escrever("Digite sua idade: ");
    ler(idade);
    
    escrever("Digite sua altura: ");
    ler(altura);
    
    escrever("Dados informados:");
    escrever("Nome: ", nome);
    escrever("Idade: ", idade, " anos");
    escrever("Altura: ", altura, " metros");
fimprograma
```

---

## 🎯 Exemplos Práticos

### Calculadora Simples

```easylanguage
programa Calculadora;

funcao somar(a: real, b: real): real
inicio
    retornar a + b;
fim

funcao subtrair(a: real, b: real): real
inicio
    retornar a - b;
fim

funcao multiplicar(a: real, b: real): real
inicio
    retornar a * b;
fim

funcao dividir(a: real, b: real): real
inicio
    se (b != 0) entao
        retornar a / b;
    senao
        escrever("Erro: divisão por zero!");
        retornar 0;
    fimse
fim

inicio
    var num1, num2, resultado: real;
    var operacao: texto;
    
    escrever("=== CALCULADORA ===");
    escrever("Digite o primeiro número: ");
    ler(num1);
    
    escrever("Digite a operação (+, -, *, /): ");
    ler(operacao);
    
    escrever("Digite o segundo número: ");
    ler(num2);
    
    se (operacao == "+") entao
        resultado = somar(num1, num2);
    senao
        se (operacao == "-") entao
            resultado = subtrair(num1, num2);
        senao
            se (operacao == "*") entao
                resultado = multiplicar(num1, num2);
            senao
                se (operacao == "/") entao
                    resultado = dividir(num1, num2);
                senao
                    escrever("Operação inválida!");
                    resultado = 0;
                fimse
            fimse
        fimse
    fimse
    
    escrever("Resultado: ", num1, " ", operacao, " ", num2, " = ", resultado);
fimprograma
```

### Sistema de Notas

```easylanguage
programa SistemaNotas;

funcao calcularMedia(notas: real[4]): real
inicio
    var soma: real = 0;
    var i: inteiro;
    
    para i = 0 ate 3 faca
        soma = soma + notas[i];
    fimpara
    
    retornar soma / 4;
fim

funcao determinarConceito(media: real): texto
inicio
    se (media >= 9.0) entao
        retornar "A";
    senao
        se (media >= 7.0) entao
            retornar "B";
        senao
            se (media >= 5.0) entao
                retornar "C";
            senao
                retornar "D";
            fimse
        fimse
    fimse
fim

inicio
    var notas: real[4];
    var media: real;
    var conceito: texto;
    var i: inteiro;
    var nome: texto;
    
    escrever("=== SISTEMA DE NOTAS ===");
    escrever("Digite o nome do aluno: ");
    ler(nome);
    
    para i = 0 ate 3 faca
        escrever("Digite a nota ", (i + 1), ": ");
        ler(notas[i]);
    fimpara
    
    media = calcularMedia(notas);
    conceito = determinarConceito(media);
    
    escrever("=== RELATÓRIO ===");
    escrever("Aluno: ", nome);
    escrever("Média: ", media);
    escrever("Conceito: ", conceito);
    
    se (conceito == "D") entao
        escrever("Status: REPROVADO");
    senao
        escrever("Status: APROVADO");
    fimse
fimprograma
```

### Jogo de Adivinhação

```easylanguage
programa JogoAdivinhacao;

inicio
    var numeroSecreto: inteiro = 42; // Em uma versão real, seria aleatório
    var palpite: inteiro;
    var tentativas: inteiro = 0;
    var acertou: logico = falso;
    
    escrever("=== JOGO DE ADIVINHAÇÃO ===");
    escrever("Tente adivinhar o número entre 1 e 100!");
    
    enquanto (nao acertou e tentativas < 10) faca
        tentativas = tentativas + 1;
        escrever("Tentativa ", tentativas, ": ");
        ler(palpite);
        
        se (palpite == numeroSecreto) entao
            acertou = verdadeiro;
            escrever("PARABÉNS! Você acertou em ", tentativas, " tentativas!");
        senao
            se (palpite < numeroSecreto) entao
                escrever("Muito baixo! Tente um número maior.");
            senao
                escrever("Muito alto! Tente um número menor.");
            fimse
        fimse
    fimenquanto
    
    se (nao acertou) entao
        escrever("Fim de jogo! O número era ", numeroSecreto);
    fimse
fimprograma
```

---

## ⚠️ Mensagens de Erro

### Erros Léxicos

| Erro | Descrição | Exemplo |
|------|-----------|---------|
| `Token inválido` | Caractere não reconhecido | `@## Manual do Usuário - EasyLanguage

## 📚 Guia Completo da Linguagem de Programação Educacional

### Versão 1.0 | Compilador EasyLanguage

---

## 📋 Índice

1. [Introdução](#introdução)
2. [Primeiros Passos](#primeiros-passos)
3. [Sintaxe Básica](#sintaxe-básica)
4. [Tipos de Dados](#tipos-de-dados)
5. [Variáveis e Constantes](#variáveis-e-constantes)
6. [Operadores](#operadores)
7. [Estruturas de Controle](#estruturas-de-controle)
8. [Funções e Procedimentos](#funções-e-procedimentos)
9. [Estruturas de Dados](#estruturas-de-dados)
10. [Entrada e Saída](#entrada-e-saída)
11. [Exemplos Práticos](#exemplos-práticos)
12. [Mensagens de Erro](#mensagens-de-erro)
13. [Dicas e Boas Práticas](#dicas-e-boas-práticas)
14. [FAQ](#faq)

---

## 🌟 Introdução

A **EasyLanguage** é uma linguagem de programação educacional desenvolvida especificamente para facilitar o aprendizado de programação. Com sintaxe clara em português e tipagem estática, ela oferece um ambiente seguro e intuitivo para iniciantes.

### Características Principais

- ✅ **Sintaxe em Português**: Palavras-chave familiares
- ✅ **Tipagem Estática**: Detecção de erros em tempo de compilação
- ✅ **Estruturas Fundamentais**: Variáveis, funções, loops e condicionais
- ✅ **Interface Web**: Editor online com destaque de sintaxe
- ✅ **Feedback Imediato**: Análise em tempo real do código

---

## 🚀 Primeiros Passos

### 1. Acessando o Compilador

1. Abra seu navegador web
2. Acesse `http://localhost:8080` (após iniciar a aplicação)
3. Você verá a interface do **Compilador EasyLanguage**

### 2. Seu Primeiro Programa

Digite o seguinte código no editor:

```easylanguage
programa MeuPrimeiroPrograma;

inicio
    escrever("Olá, mundo da programação!");
fimprograma
```

### 3. Compilando e Executando

1. Clique no botão **"Compilar"**
2. Visualize o resultado na aba **"Saída"**
3. Explore as abas **"Java Gerado"** e **"Símbolos"** para entender o processo

---

## 📝 Sintaxe Básica

### Estrutura de um Programa

Todo programa EasyLanguage segue esta estrutura básica:

```easylanguage
programa NomeDoPrograma;

// Declarações globais (opcional)
// Funções e procedimentos (opcional)

inicio
    // Código principal do programa
fimprograma
```

### Comentários

```easylanguage
// Comentário de linha única

/*
   Comentário de
   múltiplas linhas
*/
```

### Identificadores

- Devem começar com letra ou underscore (`_`)
- Podem conter letras, números e underscores
- Não podem ser palavras reservadas
- São **case-sensitive**

**Válidos**: `idade`, `nomeCompleto`, `_contador`, `valor1`
**Inválidos**: `1idade`, `nome-completo`, `se`, `para`

### Palavras Reservadas

```
programa, fimprograma, inicio, fim, se, senao, fimse, enquanto, 
fimenquanto, para, ate, faca, fimpara, funcao, procedimento, 
retornar, var, const, inteiro, real, logico, texto, verdadeiro, 
falso, ler, escrever, e, ou, nao
```

---

## 🏷️ Tipos de Dados

### Tipos Primitivos

| Tipo | Descrição | Exemplo |
|------|-----------|---------|
| `inteiro` | Números inteiros | `42`, `-15`, `0` |
| `real` | Números decimais | `3.14`, `-2.5`, `0.0` |
| `logico` | Valores lógicos | `verdadeiro`, `falso` |
| `texto` | Cadeias de caracteres | `"Olá"`, `"123"`, `""` |

### Literais

```easylanguage
// Inteiros
42
-15
0

// Reais
3.14
-2.5
0.0

// Lógicos
verdadeiro
falso

// Textos
"Olá, mundo!"
"O valor é: "
""  // string vazia
```

---

## 📊 Variáveis e Constantes

### Declaração de Variáveis

```easylanguage
programa ExemploVariaveis;

inicio
    var idade: inteiro;
    var nome: texto;
    var salario: real;
    var ativo: logico;
    
    // Declaração com inicialização
    var contador: inteiro = 0;
    var pi: real = 3.14159;
fimprograma
```

### Declaração de Constantes

```easylanguage
programa ExemploConstantes;

const PI: real = 3.14159;
const MAXIMO: inteiro = 100;
const TITULO: texto = "Sistema de Gestão";

inicio
    escrever("O valor de PI é: ", PI);
fimprograma
```

### Atribuição

```easylanguage
// Atribuição simples
idade = 25;
nome = "João Silva";
ativo = verdadeiro;

// Atribuição com expressões
total = preco * quantidade;
media = (nota1 + nota2 + nota3) / 3;
```

---

## ⚡ Operadores

### Operadores Aritméticos

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `+` | Adição | `5 + 3` resulta `8` |
| `-` | Subtração | `5 - 3` resulta `2` |
| `*` | Multiplicação | `5 * 3` resulta `15` |
| `/` | Divisão | `6 / 2` resulta `3` |
| `%` | Módulo (resto) | `7 % 3` resulta `1` |

### Operadores Relacionais

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `==` | Igual a | `5 == 5` resulta `verdadeiro` |
| `!=` | Diferente de | `5 != 3` resulta `verdadeiro` |
| `<` | Menor que | `3 < 5` resulta `verdadeiro` |
| `<=` | Menor ou igual | `5 <= 5` resulta `verdadeiro` |
| `>` | Maior que | `5 > 3` resulta `verdadeiro` |
| `>=` | Maior ou igual | `5 >= 5` resulta `verdadeiro` |

### Operadores Lógicos

| Operador | Descrição | Exemplo |
|----------|-----------|---------|
| `e` | E lógico (AND) | `verdadeiro e falso` resulta `falso` |
| `ou` | OU lógico (OR) | `verdadeiro ou falso` resulta `verdadeiro` |
| `nao` | NÃO lógico (NOT) | `nao verdadeiro` resulta `falso` |

### Precedência de Operadores

1. `()` - Parênteses
2. `nao` - Negação lógica
3. `*`, `/`, `%` - Multiplicação, divisão, módulo
4. `+`, `-` - Adição, subtração
5. `<`, `<=`, `>`, `>=` - Relacionais
6. `==`, `!=` - Igualdade
7. `e` - E lógico
8. `ou` - OU lógico

---

## 🔄 Estruturas de Controle

### Estrutura Condicional (SE-SENAO)

#### Sintaxe Básica

```easylanguage
se (condicao) entao
    // comandos executados se condição for verdadeira
fimse
```

#### Com Alternativa

```easylanguage
se (condicao) entao
    // comandos para condição verdadeira
senao
    // comandos para condição falsa
fimse
```

#### Exemplo Prático

```easylanguage
programa ExemploCondicional;

inicio
    var idade: inteiro;
    
    escrever("Digite sua idade: ");
    ler(idade);
    
    se (idade >= 18) entao
        escrever("Você é maior de idade.");
    senao
        escrever("Você é menor de idade.");
    fimse
fimprograma
```

#### Condicionais Aninhadas

```easylanguage
se (nota >= 90) entao
    escrever("Excelente!");
senao
    se (nota >= 70) entao
        escrever("Bom!");
    senao
        se (nota >= 50) entao
            escrever("Regular");
        senao
            escrever("Insuficiente");
        fimse
    fimse
fimse
```

### Estrutura de Repetição (ENQUANTO)

#### Sintaxe

```easylanguage
enquanto (condicao) faca
    // comandos a serem repetidos
fimenquanto
```

#### Exemplo

```easylanguage
programa ExemploEnquanto;

inicio
    var contador: inteiro = 1;
    
    enquanto (contador <= 10) faca
        escrever("Contagem: ", contador);
        contador = contador + 1;
    fimenquanto
    
    escrever("Fim da contagem!");
fimprograma
```

### Estrutura de Repetição (PARA)

#### Sintaxe

```easylanguage
para variavel = valorInicial ate valorFinal faca
    // comandos a serem repetidos
fimpara
```

#### Exemplo

```easylanguage
programa ExemploPara;

inicio
    var i: inteiro;
    var soma: inteiro = 0;
    
    para i = 1 ate 100 faca
        soma = soma + i;
    fimpara
    
    escrever("A soma de 1 a 100 é: ", soma);
fimprograma
```

---

## 🔧 Funções e Procedimentos

### Declaração de Função

```easylanguage
funcao nomeDaFuncao(parametros): tipoRetorno
inicio
    // corpo da função
    retornar valor;
fim
```

### Exemplo de Função

```easylanguage
programa ExemploFuncao;

funcao calcularQuadrado(numero: inteiro): inteiro
inicio
    retornar numero * numero;
fim

funcao ehPar(numero: inteiro): logico
inicio
    retornar (numero % 2) == 0;
fim

inicio
    var valor: inteiro = 8;
    var quadrado: inteiro;
    
    quadrado = calcularQuadrado(valor);
    escrever("O quadrado de ", valor, " é ", quadrado);
    
    se (ehPar(valor)) entao
        escrever(valor, " é um número par.");
    senao
        escrever(valor, " é um número ímpar.");
    fimse
fimprograma
```

### Declaração de Procedimento

```easylanguage
procedimento nomeDoProc(parametros)
inicio
    // corpo do procedimento
fim
```

### Exemplo de Procedimento

```easylanguage
programa ExemploProc;

procedimento cumprimentar(nome: texto)
inicio
    escrever("Olá, ", nome, "!");
    escrever("Bem-vindo ao sistema!");
fim

procedimento mostrarInfo(nome: texto, idade: inteiro)
inicio
    escrever("Nome: ", nome);
    escrever("Idade: ", idade, " anos");
fim

inicio
    cumprimentar("Maria");
    mostrarInfo("João", 25);
fimprograma
```

### Recursão

```easylanguage
programa ExemploRecursao;

funcao fatorial(n: inteiro): inteiro
inicio
    se (n <= 1) entao
        retornar 1;
    senao
        retornar n * fatorial(n - 1);
    fimse
fim

funcao fibonacci(n: inteiro): inteiro
inicio
    se (n <= 1) entao
        retornar n;
    senao
        retornar fibonacci(n - 1) + fibonacci(n - 2);
    fimse
fim

inicio
    escrever("Fatorial de 5: ", fatorial(5));
    escrever("Fibonacci de 10: ", fibonacci(10));
fimprograma
```

---

## 📚 Estruturas de Dados

### Arrays Unidimensionais

#### Declaração

```easylanguage
var nomeArray: inteiro[tamanho];
var numeros: inteiro[10];
var nomes: texto[5];
```

#### Uso

```easylanguage
programa ExemploArray;

inicio
    var numeros: inteiro[5];
    var i: inteiro;
    
    // Preenchendo o array
    para i = 0 ate 4 faca
        numeros[i] = i * 2;
    fimpara
    
    // Exibindo o array
    para i = 0 ate 4 faca
        escrever("numeros[", i, "] = ", numeros[i]);
    fimpara
fimprograma
```

### Arrays Bidimensionais

#### Declaração

```easylanguage
var matriz: inteiro[linhas][colunas];
var tabela: inteiro[3][4];
```

#### Exemplo

```easylanguage
programa ExemploMatriz;

inicio
    var matriz: inteiro[3][3];
    var i, j: inteiro;
    
    // Preenchendo a matriz
    para i = 0 ate 2 faca
        para j = 0 ate 2 faca
            matriz[i][j] = i + j;
        fimpara
    fimpara
    
    // Exibindo a matriz
    para i = 0 ate 2 faca
        para j = 0 ate 2 faca
            escrever(matriz[i][j], " ");
        fimpara
        escrever(""); // nova linha
    fimpara
fimprograma
```

---

## 💬 Entrada e Saída

### Comando ESCREVER

#### Sintaxe

```easylanguage
escrever(expressao1, expressao2, ...);
```

#### Exemplos

```easylanguage
escrever("Olá mundo!");
escrever("A idade é: ", idade);
escrever("Soma: ", a, " + ", b, " = ", a + b);
```

### Comando LER

#### Sintaxe

```easylanguage
ler(variavel);
```

#### Exemplos

```easylanguage
programa ExemploEntradaSaida;

inicio
    var nome: texto;
    var idade: inteiro;
    var altura: real;
    
    escrever("Digite seu nome: ");
    ler(nome);
    
    escrever("Digite sua idade: ");
    ler(idade);
    
    escrever("Digite sua altura: ");
    ler(altura);
    
    escrever("Dados informados:");
    escrever("Nome: ", nome);
    escrever("Idade: ", idade, " anos");
    escrever("Altura: ", altura, " metros");
fimprograma
```

---

## 🎯 Exemplos Práticos

### Calculadora Simples

```easylanguage
programa Calculadora;

funcao somar(a: real, b: real): real
inicio
    retornar a + b;
fim

funcao subtrair(a: real, b: real): real
inicio
    retornar a - b;
fim

funcao multiplicar(a: real, b: real): real
inicio
    retornar a * b;
fim

funcao dividir(a: real, b: real): real
inicio
    se (b != 0) entao
        retornar a / b;
    senao
        escrever("Erro: divisão por zero!");
        retornar 0;
    fimse
fim

inicio
    var num1, num2, resultado: real;
    var operacao: texto;
    
    escrever("=== CALCULADORA ===");
    escrever("Digite o primeiro número: ");
    ler(num1);
    
    escrever("Digite a operação (+, -, *, /): ");
    ler(operacao);
    
    escrever("Digite o segundo número: ");
    ler(num2);
    
 |
| `String não fechada` | Aspas não fechadas | `"Olá mundo` |
| `Número mal formado` | Formato numérico inválido | `3.14.15` |

### Erros Sintáticos

| Erro | Descrição | Solução |
|------|-----------|---------|
| `';' esperado` | Ponto e vírgula ausente | Adicione `;` no final da linha |
| `'fimse' esperado` | Estrutura condicional não fechada | Adicione `fimse` |
| `Expressão esperada` | Expressão incompleta | Complete a expressão |

### Erros Semânticos

| Erro | Descrição | Solução |
|------|-----------|---------|
| `Variável não declarada` | Uso de variável inexistente | Declare a variável antes do uso |
| `Tipos incompatíveis` | Atribuição de tipos diferentes | Verifique os tipos das variáveis |
| `Função não encontrada` | Chamada de função inexistente | Declare a função antes do uso |

### Exemplos de Correção

#### ❌ Erro:
```easylanguage
programa Exemplo;
inicio
    var idade: inteiro;
    idade = "25"  // Erro: tipos incompatíveis
fimprograma
```

#### ✅ Correção:
```easylanguage
programa Exemplo;
inicio
    var idade: inteiro;
    idade = 25;  // Correto: inteiro para inteiro
fimprograma
```

---

## 💡 Dicas e Boas Práticas

### Nomenclatura

#### ✅ Boas Práticas
```easylanguage
var idadeUsuario: inteiro;
var nomeCompleto: texto;
var salarioMensal: real;

funcao calcularImposto(salario: real): real
procedimento exibirRelatorio()
```

#### ❌ Evite
```easylanguage
var x: inteiro;
var abc: texto;
var temp: real;

funcao f(x: real): real
```

### Organização do Código

#### Estrutura Recomendada
```easylanguage
programa NomePrograma;

// 1. Constantes globais
const PI: real = 3.14159;

// 2. Funções e procedimentos
funcao calcularArea(raio: real): real
inicio
    retornar PI * raio * raio;
fim

// 3. Programa principal
inicio
    // código principal aqui
fimprograma
```

### Comentários Eficazes

#### ✅ Bons Comentários
```easylanguage
// Calcula o fatorial de um número usando recursão
funcao fatorial(n: inteiro): inteiro
inicio
    // Caso base: fatorial de 0 ou 1 é 1
    se (n <= 1) entao
        retornar 1;
    senao
        // Caso recursivo: n! = n * (n-1)!
        retornar n * fatorial(n - 1);
    fimse
fim
```

### Tratamento de Erros

```easylanguage
funcao dividir(a: real, b: real): real
inicio
    // Verifica divisão por zero
    se (b == 0) entao
        escrever("Erro: Divisão por zero não é permitida!");
        retornar 0;
    fimse
    
    retornar a / b;
fim
```

### Validação de Entrada

```easylanguage
inicio
    var idade: inteiro;
    
    escrever("Digite sua idade (0-120): ");
    ler(idade);
    
    // Valida a entrada
    se (idade < 0 ou idade > 120) entao
        escrever("Idade inválida! Digite um valor entre 0 e 120.");
    senao
        escrever("Idade válida: ", idade, " anos.");
    fimse
fimprograma
```

---

## ❓ FAQ (Perguntas Frequentes)

### 1. **Como declarar múltiplas variáveis do mesmo tipo?**

```easylanguage
// Forma 1: Individual
var nome: texto;
var sobrenome: texto;

// Forma 2: Na mesma linha (não suportado atualmente)
// var nome, sobrenome: texto;  // NÃO FUNCIONA
```

### 2. **Posso usar acentos em identificadores?**

❌ **Não recomendado**. Use apenas letras sem acentos:
```easylanguage
// Evite
var código: inteiro;

// Prefira
var codigo: inteiro;
```

### 3. **Como trabalhar com arrays de tamanho variável?**

Atualmente não há suporte para arrays dinâmicos. O tamanho deve ser especificado na declaração:

```easylanguage
var numeros: inteiro[10];  // Tamanho fixo
```

### 4. **É possível passar arrays para funções?**

Sim, arrays podem ser passados como parâmetros:

```easylanguage
funcao somarArray(arr: inteiro[5]): inteiro
inicio
    var soma: inteiro = 0;
    var i: inteiro;
    
    para i = 0 ate 4 faca
        soma = soma + arr[i];
    fimpara
    
    retornar soma;
fim
```

### 5. **Como debug meu código?**

1. Use `escrever()` para exibir valores de variáveis
2. Divida problemas complexos em funções menores
3. Teste cada parte do código separadamente
4. Verifique a aba "Símbolos" no compilador

### 6. **Posso importar bibliotecas externas?**

Atualmente não há suporte para importação de bibliotecas. Use apenas as funcionalidades built-in.

### 7. **Como converter entre tipos?**

A conversão automática é limitada. Para converter:

```easylanguage
// Inteiro para real (automático em operações)
var real_num: real = 5;  // 5 vira 5.0

// Para outros tipos, use funções personalizadas
funcao realParaInteiro(valor: real): inteiro
inicio
    // Implementação simples (trunca)
    // Em uma versão mais completa, haveria funções built-in
    retornar valor;  // Simplificado
fim
```

### 8. **Qual o limite de recursão?**

O limite depende da implementação, mas evite recursões muito profundas (>1000 níveis).

---

## 📧 Suporte e Recursos

### Obtendo Ajuda

1. **Documentação**: Consulte este manual
2. **Exemplos**: Use a aba "Exemplos de Código" no compilador
3. **Mensagens de Erro**: Leia atentamente as mensagens do compilador
4. **Professor**: Consulte o professor da disciplina

### Recursos Online

- 🌐 **Documentação ANTLR**: https://www.antlr.org
- 📚 **Livro Texto**: Compiladores - Aho, Sethi, Ullman, Lam
- 🎓 **Material da Disciplina**: Consulte o ambiente virtual

### Contribuições

Este é um projeto educacional em desenvolvimento. Sugestões e melhorias são bem-vindas!

---

## 📈 Próximos Passos

Após dominar os conceitos básicos da EasyLanguage:

1. **Pratique com Exercícios**: Implemente algoritmos clássicos
2. **Explore Recursão**: Desenvolva funções recursivas complexas
3. **Projetos Maiores**: Crie programas com múltiplas funções
4. **Estude Compiladores**: Entenda como a linguagem funciona internamente

### Sugestões de Projetos

- 🎮 **Jogos Simples**: Jogo da velha, pedra-papel-tesoura
- 🧮 **Calculadoras**: Científica, conversão de unidades
- 📊 **Sistemas**: Cadastro, relatórios simples
- 🔢 **Algoritmos**: Ordenação, busca, matemática

---

**Parabéns!** Você agora possui o conhecimento necessário para programar em EasyLanguage. Pratique, experimente e divirta-se aprendendo programação!

---

*Manual do Usuário - EasyLanguage v1.0*  
*Faculdade Engenheiro Salvador Arena*  
*Disciplina: Compiladores - 1º Semestre/2025*
