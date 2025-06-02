package br.edu.cefsa.controller;

import br.edu.cefsa.model.CompilationResult;
import br.edu.cefsa.service.CompilerService;
import br.edu.cefsa.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IDEController {

  @Autowired private CompilerService compilerService;

  @Autowired private ExampleService exampleService;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("sourceCode", exampleService.getExample("Hello World"));
    model.addAttribute("examples", exampleService.getExamples());
    return "ide";
  }

  @PostMapping(value = "/compile", consumes = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public CompilationResult compile(@RequestBody String sourceCode) {
    return compilerService.compile(sourceCode);
  }

  @PostMapping("/run")
  @ResponseBody
  public CompilationResult run(@RequestBody String sourceCode) {
    return compilerService.run(sourceCode);
  }

  @GetMapping(value = "/example/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String getExample(@PathVariable("name") String name) {
    return exampleService.getExample(name);
  }
}

