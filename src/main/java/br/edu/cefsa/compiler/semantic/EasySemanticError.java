package br.edu.cefsa.compiler.semantic;

import java.util.Objects;

/**
 * Classe para representar erros e avisos semânticos encontrados durante a análise
 * semântica do código EduScript.
 * 
 * Esta classe estende RuntimeException para permitir que erros semânticos
 * sejam tratados como exceções não verificadas, e também representa avisos (warnings)
 * que indicam situações que não impedem a compilação, mas podem indicar
 * problemas ou más práticas no código.
 */
public class EasySemanticError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Tipos de erros semânticos
    public enum ErrorType {
        VARIABLE_NOT_DECLARED("Variável não declarada"),
        VARIABLE_ALREADY_DECLARED("Variável já declarada"),
        TYPE_MISMATCH("Incompatibilidade de tipos"),
        INVALID_OPERATION("Operação inválida"),
        FUNCTION_NOT_DECLARED("Função não declarada"),
        WRONG_PARAMETER_COUNT("Número incorreto de parâmetros"),
        DIVISION_BY_ZERO("Divisão por zero"),
        INVALID_ASSIGNMENT("Atribuição inválida"),
        SCOPE_ERROR("Erro de escopo"),
        RETURN_TYPE_MISMATCH("Tipo de retorno incompatível"),
        INVALID_WHILE_CONDITION("Condição inválida no while");
        
        private final String description;

        ErrorType(String description) {
            this.description = Objects.requireNonNull(description);
        }

        public String getDescription() {
            return description;
        }
    }

    // Tipos de warnings semânticos
    public enum WarningType {
        UNUSED_VARIABLE("Variável declarada mas não usada"),
        DEPRECATED_FUNCTION("Uso de função depreciada"),
        POSSIBLE_LOSS_OF_PRECISION("Possível perda de precisão"),
        UNREACHABLE_CODE("Código inacessível"),
        UNUSED_PARAMETER("Parâmetro não utilizado");

        private final String description;

        WarningType(String description) {
            this.description = Objects.requireNonNull(description);
        }

        public String getDescription() {
            return description;
        }
    }

    private final ErrorType errorType;
    private final WarningType warningType;
    private final int line;
    private final int column;
    private final String symbol;
    private final String additionalInfo;

    // Construtor para erro com mensagem simples
    public EasySemanticError(String message) {
        super(Objects.requireNonNull(message));
        this.errorType = null;
        this.warningType = null;
        this.line = -1;
        this.column = -1;
        this.symbol = null;
        this.additionalInfo = null;
    }

    // Construtor para erro com tipo e mensagem
    public EasySemanticError(ErrorType errorType, String message) {
        super(errorType.getDescription() + ": " + Objects.requireNonNull(message));
        this.errorType = Objects.requireNonNull(errorType);
        this.warningType = null;
        this.line = -1;
        this.column = -1;
        this.symbol = null;
        this.additionalInfo = message;
    }

    // Construtor para erro com tipo, símbolo e mensagem
    public EasySemanticError(ErrorType errorType, String symbol, String message) {
        super(errorType.getDescription() + " '" + Objects.requireNonNull(symbol) + "': " + Objects.requireNonNull(message));
        this.errorType = Objects.requireNonNull(errorType);
        this.warningType = null;
        this.symbol = symbol;
        this.line = -1;
        this.column = -1;
        this.additionalInfo = message;
    }

    // Construtor completo para erro com posição no código
    public EasySemanticError(ErrorType errorType, String symbol, int line, int column, String message) {
        super(String.format("[Linha %d, Coluna %d] %s '%s': %s", 
              line, column, errorType.getDescription(), symbol, message));
        this.errorType = Objects.requireNonNull(errorType);
        this.warningType = null;
        this.symbol = symbol;
        this.line = line;
        this.column = column;
        this.additionalInfo = message;
    }

    // Construtor para erro com tipo e posição (sem símbolo)
    public EasySemanticError(ErrorType errorType, int line, int column, String message) {
        super(String.format("[Linha %d, Coluna %d] %s: %s", 
              line, column, errorType.getDescription(), message));
        this.errorType = Objects.requireNonNull(errorType);
        this.warningType = null;
        this.line = line;
        this.column = column;
        this.symbol = null;
        this.additionalInfo = message;
    }

    // Construtor completo para warning
    public EasySemanticError(WarningType warningType, String symbol, int line, int column, String message) {
        super(String.format("[Linha %d, Coluna %d] Warning: %s%s: %s",
            line,
            column,
            warningType.getDescription(),
            (symbol != null && !symbol.isEmpty()) ? " '" + symbol + "'" : "",
            message));
        this.warningType = Objects.requireNonNull(warningType);
        this.errorType = null;
        this.symbol = symbol;
        this.line = line;
        this.column = column;
        this.additionalInfo = message;
    }

    // Getters
    public ErrorType getErrorType() {
        return errorType;
    }

    public WarningType getWarningType() {
        return warningType;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Retorna uma representação detalhada do erro ou warning.
     * @return Detalhes em formato de string.
     */
    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();

        if (errorType != null) {
            sb.append("Tipo: ").append(errorType.getDescription()).append("\n");
        }
        if (warningType != null) {
            sb.append("Warning: ").append(warningType.getDescription()).append("\n");
        }
        if (line > 0 && column > 0) {
            sb.append("Posição: Linha ").append(line).append(", Coluna ").append(column).append("\n");
        }
        if (symbol != null && !symbol.isEmpty()) {
            sb.append("Símbolo: ").append(symbol).append("\n");
        }
        sb.append("Descrição: ").append(getMessage());

        return sb.toString();
    }

    // Métodos estáticos para criar erros comuns

    public static EasySemanticError variableNotDeclared(String varName, int line, int column) {
        return new EasySemanticError(ErrorType.VARIABLE_NOT_DECLARED, varName, line, column,
                "A variável '" + varName + "' não foi declarada neste escopo");
    }

    public static EasySemanticError variableAlreadyDeclared(String varName, int line, int column) {
        return new EasySemanticError(ErrorType.VARIABLE_ALREADY_DECLARED, varName, line, column,
                "A variável '" + varName + "' já foi declarada neste escopo");
    }

    public static EasySemanticError typeMismatch(String expected, String found, int line, int column) {
        return new EasySemanticError(ErrorType.TYPE_MISMATCH, line, column,
                String.format("Esperado tipo '%s', mas encontrado '%s'", expected, found));
    }

    public static EasySemanticError invalidOperation(String operation, String type, int line, int column) {
        return new EasySemanticError(ErrorType.INVALID_OPERATION, line, column,
                String.format("Operação '%s' não é válida para o tipo '%s'", operation, type));
    }

    public static EasySemanticError functionNotDeclared(String funcName, int line, int column) {
        return new EasySemanticError(ErrorType.FUNCTION_NOT_DECLARED, funcName, line, column,
                "A função '" + funcName + "' não foi declarada");
    }

    public static EasySemanticError wrongParameterCount(String funcName, int expected, int found, int line, int column) {
        return new EasySemanticError(ErrorType.WRONG_PARAMETER_COUNT, funcName, line, column,
                String.format("A função '%s' espera %d parâmetros, mas foram fornecidos %d", 
                             funcName, expected, found));
    }

    public static EasySemanticError divisionByZero(int line, int column) {
        return new EasySemanticError(ErrorType.DIVISION_BY_ZERO, line, column,
                "Tentativa de divisão por zero detectada");
    }

    public static EasySemanticError invalidAssignment(String varName, String varType, String exprType, int line, int column) {
        return new EasySemanticError(ErrorType.INVALID_ASSIGNMENT, varName, line, column,
                String.format("Não é possível atribuir valor do tipo '%s' à variável '%s' do tipo '%s'", 
                             exprType, varName, varType));
    }

    public static EasySemanticError invalidWhileCondition(int line, int column, String message) {
        return new EasySemanticError(ErrorType.INVALID_WHILE_CONDITION, line, column, message);
    }

    // Métodos estáticos para criar warnings comuns

    public static EasySemanticError unusedVariableWarning(String varName, int line, int column) {
        return new EasySemanticError(WarningType.UNUSED_VARIABLE, varName, line, column,
                "A variável '" + varName + "' foi declarada mas não foi utilizada");
    }

    public static EasySemanticError deprecatedFunctionWarning(String funcName, int line, int column) {
        return new EasySemanticError(WarningType.DEPRECATED_FUNCTION, funcName, line, column,
                "A função '" + funcName + "' é depreciada e seu uso não é recomendado");
    }

    public static EasySemanticError possibleLossOfPrecisionWarning(String fromType, String toType, int line, int column) {
        return new EasySemanticError(WarningType.POSSIBLE_LOSS_OF_PRECISION, null, line, column,
                String.format("Conversão de '%s' para '%s' pode causar perda de precisão", fromType, toType));
    }

    public static EasySemanticError unreachableCodeWarning(int line, int column) {
        return new EasySemanticError(WarningType.UNREACHABLE_CODE, null, line, column,
                "Código após este ponto é inalcancável");
    }

    public static EasySemanticError unusedParameterWarning(String paramName, int line, int column) {
        return new EasySemanticError(WarningType.UNUSED_PARAMETER, paramName, line, column,
                "O parâmetro '" + paramName + "' não foi utilizado na função");
    }

    public static EasySemanticError variableDeclaredButNotUsed(String varName, int line, int column) {
    return new EasySemanticError(ErrorType.SCOPE_ERROR, varName, line, column,
            "A variável '" + varName + "' foi declarada mas não foi utilizada");
}

    @Override
    public String toString() {
        return getDetailedMessage();
    }
}
