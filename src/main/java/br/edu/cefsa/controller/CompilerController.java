package br.edu.cefsa.controller;

import br.edu.cefsa.compiler.datastructures.EasySymbol;
import br.edu.cefsa.compiler.datastructures.EasySymbolTable;
import br.edu.cefsa.compiler.main.EasyLanguageCompiler;
import br.edu.cefsa.compiler.main.EasyLanguageCompiler.CompilationResult;
import com.google.gson.Gson;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compile")
@CrossOrigin(origins = "*")
public class CompilerController {

  private Gson gson = new Gson();

  @PostMapping
  public ResponseEntity<CompilerResponse> compileCode(@RequestBody String sourceCode) {
    CompilerResponse compilerResponse = new CompilerResponse();

    if (sourceCode == null || sourceCode.trim().isEmpty()) {
      compilerResponse.setSuccess(false);
      compilerResponse.setMessage("Código fonte vazio");
      compilerResponse.getErrors().add("Por favor, forneça código para compilar");
      return new ResponseEntity<>(compilerResponse, HttpStatus.BAD_REQUEST);
    }

    try {
      CompilationResult result = EasyLanguageCompiler.compile(sourceCode);

      compilerResponse.setSuccess(result.isSuccess());
      compilerResponse.setMessage(result.getMessage());
      compilerResponse.setErrors(result.getErrors());
      compilerResponse.setWarnings(result.getWarnings());
      compilerResponse.setGeneratedCode(result.getGeneratedCode());

      if (result.getSymbolTable() != null) {
        convertSymbolTable(result.getSymbolTable(), compilerResponse);
      }

      return ResponseEntity.ok(compilerResponse);
    } catch (Exception e) {
      compilerResponse.setSuccess(false);
      compilerResponse.setMessage("Erro interno do servidor");
      compilerResponse.getErrors().add("Erro: " + e.getMessage());
      return new ResponseEntity<>(compilerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void convertSymbolTable(EasySymbolTable symbolTable, CompilerResponse response) {
    for (EasySymbol symbol : symbolTable.getAll()) {
      SymbolTableEntry entry =
          new SymbolTableEntry(
              symbol.getName(),
              String.valueOf(symbol.getType()),
              symbol.isUsed(),
              symbol.isInitialized(),
              symbol.getLine());
      response.getSymbolTable().add(entry);
    }
  }

  // Classes internas
  public static class CompilerResponse {
    private boolean success;
    private String message;
    private List<String> errors = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private String generatedCode;
    private List<SymbolTableEntry> symbolTable = new ArrayList<>();

    // Getters e setters...
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

    public String getGeneratedCode() {
      return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
      this.generatedCode = generatedCode;
    }

    public List<SymbolTableEntry> getSymbolTable() {
      return symbolTable;
    }

    public void setSymbolTable(List<SymbolTableEntry> symbolTable) {
      this.symbolTable = symbolTable;
    }
  }

  public static class SymbolTableEntry {
    private String name;
    private String type;
    private boolean used;
    private boolean initialized;
    private int line;

    public SymbolTableEntry(String name, String type, boolean used, boolean initialized, int line) {
      this.name = name;
      this.type = type;
      this.used = used;
      this.initialized = initialized;
      this.line = line;
    }

    // Getters
    public String getName() {
      return name;
    }

    public String getType() {
      return type;
    }

    public boolean isUsed() {
      return used;
    }

    public boolean isInitialized() {
      return initialized;
    }

    public int getLine() {
      return line;
    }
  }
}
