package br.edu.cefsa.service;

import br.edu.cefsa.compiler.parser.EasyLanguageLexer;
import br.edu.cefsa.compiler.parser.EasyLanguageParser;
import br.edu.cefsa.model.CompilationResult;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.*;
import org.springframework.stereotype.Service;

@Service
public class CompilerService {

  // Listener customizado para capturar erros de sintaxe
  private static class SyntaxErrorListener extends BaseErrorListener {
    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg,
        RecognitionException e) {
      errors.add(String.format("Linha %d:%d - %s", line, charPositionInLine, msg));
    }

    public List<String> getErrors() {
      return errors;
    }

    public boolean hasErrors() {
      return !errors.isEmpty();
    }
  }

  public CompilationResult compile(String sourceCode) {
    try {
      // Validação de entrada
      if (sourceCode == null || sourceCode.trim().isEmpty()) {
        return CompilationResult.builder().success(false).error("Código-fonte está vazio.").build();
      }

      // Transforma o código em um fluxo de caracteres
      CharStream input =
          CharStreams.fromStream(
              new ByteArrayInputStream(sourceCode.getBytes(StandardCharsets.UTF_8)));

      // Cria o lexer e o parser
      EasyLanguageLexer lexer = new EasyLanguageLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      EasyLanguageParser parser = new EasyLanguageParser(tokens);

      // Remove listeners padrão e adiciona o customizado
      SyntaxErrorListener errorListener = new SyntaxErrorListener();
      lexer.removeErrorListeners();
      parser.removeErrorListeners();
      lexer.addErrorListener(errorListener);
      parser.addErrorListener(errorListener);

      // Inicia a análise sintática (entry point da gramática)
      EasyLanguageParser.ProgContext tree = parser.prog();

      // Se houver erros de sintaxe
      if (errorListener.hasErrors()) {
        return CompilationResult.builder()
            .success(false)
            .error("Erros de compilação:\n" + String.join("\n", errorListener.getErrors()))
            .build();
      }

      // Compilação bem-sucedida
      return CompilationResult.builder()
          .success(true)
          .output("Compilação realizada com sucesso!")
          .parseTree(tree.toStringTree(parser))
          .build();

    } catch (Exception e) {
      // Log interno (opcional, pode ser substituído por um Logger real)
      e.printStackTrace();

      return CompilationResult.builder()
          .success(false)
          .error("Erro interno ao compilar: " + e.getMessage())
          .build();
    }
  }

  public CompilationResult run(String sourceCode) {
    // Primeiro, compila o código
    CompilationResult result = compile(sourceCode);

    if (!result.isSuccess()) {
      return result; // Se não compilou, retorna o erro de compilação
    }

    // Aqui poderia entrar a execução da AST (futuramente)
    return CompilationResult.builder()
        .success(true)
        .output("Execução simulada com sucesso!") // Placeholder
        .build();
  }
}
