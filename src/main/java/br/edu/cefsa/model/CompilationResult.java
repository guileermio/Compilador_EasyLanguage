package br.edu.cefsa.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompilationResult {
  private boolean success;
  private String output;
  private String error;
  private String parseTree;
}

