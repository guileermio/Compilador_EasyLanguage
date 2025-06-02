package br.edu.cefsa.compiler.semantic;

import br.edu.cefsa.compiler.semantic.EasySemanticError;

import java.util.*;

/**
 * Analisador Semântico para a linguagem EduScript
 * 
 * Responsável por:
 * - Verificação de tipos
 * - Controle de escopo de variáveis
 * - Validação de declarações e uso de variáveis
 * - Verificação de compatibilidade de operações
 * - Suporte a comandos while
 */
public class EasySemanticAnalyzer {
    
    // Tipos de dados suportados
    public enum DataType {
        INTEGER("inteiro"),
        REAL("real"),
        STRING("texto"),
        BOOLEAN("logico"),
        VOID("vazio"),
        UNKNOWN("desconhecido");
        
        private final String typeName;
        
        DataType(String typeName) {
            this.typeName = typeName;
        }
        
        public String getTypeName() {
            return typeName;
        }
        
        public static DataType fromString(String type) {
            for (DataType dt : DataType.values()) {
                if (dt.typeName.equals(type)) {
                    return dt;
                }
            }
            return UNKNOWN;
        }
    }
    
    // Classe para representar símbolos na tabela
    public static class Symbol {
        private String name;
        private DataType type;
        private boolean initialized;
        private int declarationLine;
        private Object value;
        
        public Symbol(String name, DataType type, int declarationLine) {
            this.name = name;
            this.type = type;
            this.declarationLine = declarationLine;
            this.initialized = false;
            this.value = null;
        }
        
        // Getters e Setters
        public String getName() { return name; }
        public DataType getType() { return type; }
        public boolean isInitialized() { return initialized; }
        public void setInitialized(boolean initialized) { this.initialized = initialized; }
        public int getDeclarationLine() { return declarationLine; }
        public Object getValue() { return value; }
        public void setValue(Object value) { this.value = value; }
        
        @Override
        public String toString() {
            return String.format("Symbol{name='%s', type=%s, initialized=%s, line=%d}", 
                               name, type, initialized, declarationLine);
        }
    }
    
    // Tabela de símbolos com suporte a escopos
    private Stack<Map<String, Symbol>> symbolTable;
    private List<EasySemanticError> errors;
    private int currentLine;
    private int currentColumn;
    
    public EasySemanticAnalyzer() {
        this.symbolTable = new Stack<>();
        this.errors = new ArrayList<>();
        this.currentLine = 1;
        this.currentColumn = 1;
        
        // Inicia com escopo global
        enterScope();
    }
    
    /**
     * Entra em um novo escopo
     */
    public void enterScope() {
        symbolTable.push(new HashMap<>());
    }
    
    /**
     * Sai do escopo atual
     */
    public void exitScope() {
        if (!symbolTable.isEmpty()) {
            symbolTable.pop();
        }
    }
    
    /**
     * Define a posição atual no código fonte
     */
    public void setPosition(int line, int column) {
        this.currentLine = line;
        this.currentColumn = column;
    }
    
    /**
     * Declara uma nova variável
     */
    public void declareVariable(String name, DataType type) {
        if (symbolTable.isEmpty()) {
            addError(EasySemanticError.ErrorType.SCOPE_ERROR, "Nenhum escopo ativo");
            return;
        }
        
        Map<String, Symbol> currentScope = symbolTable.peek();
        
        // Verifica se a variável já foi declarada no escopo atual
        if (currentScope.containsKey(name)) {
            addError(EasySemanticError.variableAlreadyDeclared(name, currentLine, currentColumn));
            return;
        }
        
        // Adiciona a variável ao escopo atual
        Symbol symbol = new Symbol(name, type, currentLine);
        currentScope.put(name, symbol);
    }
    
    /**
     * Verifica se uma variável foi declarada
     */
    public boolean isVariableDeclared(String name) {
        return lookupVariable(name) != null;
    }
    
    /**
     * Busca uma variável em todos os escopos (do mais interno para o mais externo)
     */
    public Symbol lookupVariable(String name) {
        for (int i = symbolTable.size() - 1; i >= 0; i--) {
            Map<String, Symbol> scope = symbolTable.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }
    
    /**
     * Verifica o uso de uma variável
     */
    public DataType checkVariableUsage(String name) {
        Symbol symbol = lookupVariable(name);
        
        if (symbol == null) {
            addError(EasySemanticError.variableNotDeclared(name, currentLine, currentColumn));
            return DataType.UNKNOWN;
        }
        
        return symbol.getType();
    }
    
    /**
     * Verifica uma atribuição
     */
    public void checkAssignment(String varName, DataType exprType) {
        Symbol symbol = lookupVariable(varName);
        
        if (symbol == null) {
            addError(EasySemanticError.variableNotDeclared(varName, currentLine, currentColumn));
            return;
        }
        
        // Verifica compatibilidade de tipos
        if (!isTypeCompatible(symbol.getType(), exprType)) {
            addError(EasySemanticError.invalidAssignment(varName, symbol.getType().getTypeName(), 
                                                        exprType.getTypeName(), currentLine, currentColumn));
            return;
        }
        
        // Marca a variável como inicializada
        symbol.setInitialized(true);
    }
    
    /**
     * Verifica uma operação aritmética
     */
    public DataType checkArithmeticOperation(String operator, DataType leftType, DataType rightType) {
        // Verifica se os tipos são compatíveis para operações aritméticas
        if (!isArithmeticType(leftType) || !isArithmeticType(rightType)) {
            addError(EasySemanticError.invalidOperation(operator, 
                    leftType.getTypeName() + " e " + rightType.getTypeName(), 
                    currentLine, currentColumn));
            return DataType.UNKNOWN;
        }
        
        // Determina o tipo resultante
        if (leftType == DataType.REAL || rightType == DataType.REAL) {
            return DataType.REAL;
        } else {
            return DataType.INTEGER;
        }
    }
    
    /**
     * Verifica uma operação relacional
     */
    public DataType checkRelationalOperation(String operator, DataType leftType, DataType rightType) {
        // Verifica se os tipos são comparáveis
        if (!isComparableType(leftType) || !isComparableType(rightType)) {
            addError(EasySemanticError.invalidOperation(operator, 
                    leftType.getTypeName() + " e " + rightType.getTypeName(), 
                    currentLine, currentColumn));
            return DataType.UNKNOWN;
        }
        
        // Verifica compatibilidade entre os tipos
        if (!isTypeCompatible(leftType, rightType)) {
            addError(EasySemanticError.typeMismatch(leftType.getTypeName(), rightType.getTypeName(), 
                                                   currentLine, currentColumn));
            return DataType.UNKNOWN;
        }
        
        return DataType.BOOLEAN;
    }
    
    /**
     * Verifica divisão por zero (análise estática básica)
     */
    public void checkDivisionByZero(String operator, Object rightValue) {
        if ("/".equals(operator) && rightValue != null) {
            if ((rightValue instanceof Integer && (Integer) rightValue == 0) ||
                (rightValue instanceof Double && (Double) rightValue == 0.0)) {
                addError(EasySemanticError.divisionByZero(currentLine, currentColumn));
            }
        }
    }
    
    /**
     * Verifica se um tipo é aritmético
     */
    private boolean isArithmeticType(DataType type) {
        return type == DataType.INTEGER || type == DataType.REAL;
    }
    
    /**
     * Verifica se um tipo é comparável
     */
    private boolean isComparableType(DataType type) {
        return type == DataType.INTEGER || type == DataType.REAL || type == DataType.STRING;
    }
    
    /**
     * Verifica se dois tipos são compatíveis
     */
    private boolean isTypeCompatible(DataType type1, DataType type2) {
        if (type1 == type2) {
            return true;
        }
        
        // Conversão implícita de inteiro para real
        if ((type1 == DataType.INTEGER && type2 == DataType.REAL) ||
            (type1 == DataType.REAL && type2 == DataType.INTEGER)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Adiciona um erro à lista de erros
     */
    private void addError(EasySemanticError.ErrorType errorType, String message) {
        errors.add(new EasySemanticError(errorType, currentLine, currentColumn, message));
    }
    
    private void addError(EasySemanticError error) {
        errors.add(error);
    }
    
    /**
     * Verifica se houve erros semânticos
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    /**
     * Retorna a lista de erros encontrados
     */
    public List<EasySemanticError> getErrors() {
        return new ArrayList<>(errors);
    }
    
    /**
     * Retorna uma string com todos os erros formatados
     */
    public String getErrorsAsString() {
        if (errors.isEmpty()) {
            return "Nenhum erro semântico encontrado.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Erros semânticos encontrados:\n\n");
        
        for (int i = 0; i < errors.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, errors.get(i).getMessage()));
        }
        
        return sb.toString();
    }
    
    /**
     * Limpa todos os erros registrados
     */
    public void clearErrors() {
        errors.clear();
    }
    
    /**
     * Retorna informações sobre a tabela de símbolos atual
     */
    public String getSymbolTableInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TABELA DE SÍMBOLOS ===\n");
        
        for (int level = 0; level < symbolTable.size(); level++) {
            sb.append(String.format("Escopo %d:\n", level));
            Map<String, Symbol> scope = symbolTable.get(level);
            for (Symbol symbol : scope.values()) {
                sb.append(String.format("  %s\n", symbol));
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Verifica variáveis declaradas mas não utilizadas (não inicializadas)
     * Agora gerando erros (EasySemanticError) em vez de avisos.
     */
    public void checkUnusedVariables() {
        for (Map<String, Symbol> scope : symbolTable) {
            for (Symbol symbol : scope.values()) {
                if (!symbol.isInitialized()) {
                    errors.add(EasySemanticError.variableDeclaredButNotUsed(
                        symbol.getName(), symbol.getDeclarationLine(), 0));
                }
            }
        }
    }
}
