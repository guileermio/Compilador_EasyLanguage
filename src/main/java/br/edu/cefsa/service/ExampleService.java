package br.edu.cefsa.service;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

  private final Map<String, String> examples;

  public ExampleService() {
    examples = new LinkedHashMap<>();

    examples.put(
        "Atribuição & Soma",
        """
        programa
        numero x;
        numero y;
        numero z;

        y = 5;
        z = 7;
        x = y + z;
        escreva(x);

        fimprog;
        """);

    examples.put(
        "Expressão Aninhada",
        """
        programa
        numero a;
        numero b;
        numero c;
        numero d;
        numero resultado;

        a = 2;
        b = 3;
        c = 4;
        d = 5;
        resultado = (a + b) * (c - d) / (a - 1);
        escreva(resultado);

        fimprog;
        """);

    examples.put(
        "Loop While",
        """
        programa
        numero i;
        numero n;
        numero soma;

        escreva("Digite n:");
        leia(n);

        i = 1;
        soma = 0;

        enquanto (i <= n) {
            soma = soma + i;
            i = i + 1;
        }

        escreva(soma);

        fimprog;
        """);

    examples.put(
        "Fatorial com While",
        """
        programa
        numero i;
        numero n;
        numero fat;

        escreva("Digite n:");
        leia(n);

        fat = 1;
        i = 1;

        enquanto (i <= n) {
            fat = fat * i;
            i = i + 1;
        }

        escreva(fat);

        fimprog;
        """);

    examples.put(
        "Fibonacci Sequência",
        """
        programa
        numero a;
        numero b;
        numero c;
        numero i;
        numero n;

        escreva("Quantos termos?:");
        leia(n);

        a = 0;
        b = 1;
        i = 1;

        enquanto (i <= n) {
            escreva(a);
            c = a + b;
            a = b;
            b = c;
            i = i + 1;
        }

        fimprog;
        """);

    examples.put(
        "Prioridade de Operações",
        """
        programa
        numero x;
        numero y;
        numero z;

        x = 2;
        y = 3;
        z = 4;

        escreva(x + y * z);    // Esperado: 14
        escreva((x + y) * z);  // Esperado: 20

        fimprog;
        """);

    examples.put(
        "Loop Aninhado Simplificado",
        """
        programa
        numero i;
        numero j;

        i = 1;

        enquanto (i <= 3) {
            j = 1;
            enquanto (j <= 2) {
                escreva(i);
                escreva(j);
                j = j + 1;
            }
            i = i + 1;
        }

        fimprog;
        """);
  }

  public Map<String, String> getExamples() {
    return examples;
  }

  public String getExample(String name) {
    return examples.getOrDefault(name, "");
  }
}
