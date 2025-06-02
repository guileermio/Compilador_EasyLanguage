package br.edu.cefsa.controller;

import br.edu.cefsa.compiler.datastructures.EasySymbolTable;
import br.edu.cefsa.compiler.main.EasyLanguageCompiler;
import br.edu.cefsa.compiler.main.EasyLanguageCompiler.CompilationResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easylanguage")
public class EasyLanguageController {

  @PostMapping("/compile")
  public ResponseEntity<CompileResult> compile(@RequestBody String code) {
    CompileResult response = new CompileResult();

    if (code == null || code.trim().isEmpty()) {
      response.setSuccess(false);
      response.setMessage("Código vazio.");
      return ResponseEntity.badRequest().body(response);
    }

    CompilationResult compilationResult = EasyLanguageCompiler.compile(code);

    response.setSuccess(compilationResult.isSuccess());
    response.setMessage(compilationResult.getMessage());
    response.setGeneratedCode(compilationResult.getGeneratedCode());
    response.setErrors(compilationResult.getErrors());
    response.setWarnings(compilationResult.getWarnings());

    EasySymbolTable table = compilationResult.getSymbolTable();
    if (table != null) {
      response.setSymbolTable(
          table.getAll().stream()
              .map(
                  easySymbol ->
                      new Symbol(
                          easySymbol.getName(),
                          typeIntToString(easySymbol.getType()), // converte int para String
                          easySymbol.getLine(),
                          easySymbol.isUsed(),
                          easySymbol.isInitialized()))
              .collect(Collectors.toList()));
    }

    return ResponseEntity.ok(response);
  }

  private String typeIntToString(int type) {
    switch (type) {
      case 0:
        return "inteiro";
      case 1:
        return "texto";
      case 2:
        return "logico";
      // adicione outros tipos conforme seu compilador
      default:
        return "desconhecido";
    }
  }

  public static class CompileResult {
    private boolean success;
    private String message;
    private String generatedCode;
    private List<String> errors;
    private List<String> warnings;
    private List<Symbol> symbolTable;

    public boolean isSuccess() {
      return success;
    }

    public void setSuccess(boolean success) {
      this.success = success;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getGeneratedCode() {
      return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
      this.generatedCode = generatedCode;
    }

    public List<String> getErrors() {
      return errors;
    }

    public void setErrors(List<String> errors) {
      this.errors = errors;
    }

    public List<String> getWarnings() {
      return warnings;
    }

    public void setWarnings(List<String> warnings) {
      this.warnings = warnings;
    }

    public List<Symbol> getSymbolTable() {
      return symbolTable;
    }

    public void setSymbolTable(List<Symbol> symbolTable) {
      this.symbolTable = symbolTable;
    }
  }

  public static class Symbol {
    private String name;
    private String type;
    private int line;
    private boolean used;
    private boolean initialized;

    public Symbol() {}

    public Symbol(String name, String type, int line, boolean used, boolean initialized) {
      this.name = name;
      this.type = type;
      this.line = line;
      this.used = used;
      this.initialized = initialized;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public int getLine() {
      return line;
    }

    public void setLine(int line) {
      this.line = line;
    }

    public boolean isUsed() {
      return used;
    }

    public void setUsed(boolean used) {
      this.used = used;
    }

    public boolean isInitialized() {
      return initialized;
    }

    public void setInitialized(boolean initialized) {
      this.initialized = initialized;
    }
  }
}
