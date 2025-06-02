// /js/easylanguage-mode.js
CodeMirror.defineMode("easylanguage", function () {
  return {
    startState: () => ({ inString: false }),
    token: (stream, state) => {
      if (stream.sol() && stream.match("#")) {
        stream.skipToEnd();
        return "comment";
      }
      if (!state.inString && stream.match(/"/)) {
        state.inString = true;
        return "string";
      }
      if (state.inString) {
        if (stream.skipTo('"')) {
          stream.next();
          state.inString = false;
        } else stream.skipToEnd();
        return "string";
      }
      if (
        stream.match(
          /^(algoritmo|var|inicio|fimalgoritmo|se|entao|senao|fimse|enquanto|faca|fimenquanto)\b/,
          true,
        )
      )
        return "keyword";
      if (stream.match(/^(leia|escreva|escreval)\b/, true)) return "builtin";
      if (stream.match(/^[+\-*/=<>!]+/, true)) return "operator";
      if (stream.match(/^-?\d+(\.\d+)?/, true)) return "number";
      if (stream.match(/^[A-Za-z_]\w*/, true)) return "variable";
      stream.next();
      return null;
    },
  };
});
CodeMirror.defineMIME("text/x-easylanguage", "easylanguage");
