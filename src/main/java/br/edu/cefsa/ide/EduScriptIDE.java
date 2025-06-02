package br.edu.cefsa.ide;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.undo.*;

public class EduScriptIDE extends JFrame {
    private JTextPane editorPane;
    private JTextArea outputArea;
    private JTextArea errorArea;
    private JLabel statusLabel;
    private JTabbedPane bottomPane;
    private File currentFile;
    private UndoManager undoManager;
    
    // Cores para destaque de sintaxe
    private static final Color KEYWORD_COLOR = new Color(0, 0, 255);
    private static final Color STRING_COLOR = new Color(0, 128, 0);
    private static final Color COMMENT_COLOR = new Color(128, 128, 128);
    private static final Color NUMBER_COLOR = new Color(255, 0, 0);
    private static final Color OPERATOR_COLOR = new Color(128, 0, 128);
    
    // Palavras-chave do EduScript
    private static final String[] KEYWORDS = {
        "programa", "fimprograma", "inicio", "var", "inteiro", "real",
        "enquanto", "faca", "fimenquanto", "se", "senao", "fimse",
        "ler", "escrever", "funcao", "retornar", "fim"
    };
    
    public EduScriptIDE() {
        initializeComponents();
        setupMenuBar();
        setupLayout();
        setupSyntaxHighlighting();
        setupEventHandlers();
        
        setTitle("EduScript IDE - Novo Arquivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Código de exemplo inicial
        String exemploInicial = """
            programa ExemploGrupo9;
            
            var a : real;
            var b : real;
            
            inicio
                // leitura das variáveis
                ler(a);
                ler(b);
                
                // expressão com +, *, /
                a = 1 + 2 * 3 / b;
                
                // while permitido na gramática
                enquanto a > b faca
                    escrever(a);
                    a = a - 1;
                fimenquanto
                
                // fim do programa
                escrever("Fim do loop. Valor final de a: ");
                escrever(a);
                
            fimprograma
            """;
        
        editorPane.setText(exemploInicial);
        applySyntaxHighlighting();
    }
    
    private void initializeComponents() {
        // Editor principal
        editorPane = new JTextPane();
        editorPane.setFont(new Font("Consolas", Font.PLAIN, 14));
        editorPane.setBackground(Color.WHITE);
        
        // Área de saída
        outputArea = new JTextArea(10, 0);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245));
        
        // Área de erros
        errorArea = new JTextArea(10, 0);
        errorArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        errorArea.setEditable(false);
        errorArea.setBackground(new Color(255, 245, 245));
        errorArea.setForeground(Color.RED);
        
        // Status bar
        statusLabel = new JLabel("Pronto");
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // Painel inferior com abas
        bottomPane = new JTabbedPane();
        bottomPane.addTab("Saída", new JScrollPane(outputArea));
        bottomPane.addTab("Erros", new JScrollPane(errorArea));
        
        // Undo Manager
        undoManager = new UndoManager();
        editorPane.getDocument().addUndoableEditListener(undoManager);
    }
    
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Arquivo
        JMenu fileMenu = new JMenu("Arquivo");
        fileMenu.add(createMenuItem("Novo", KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), this::newFile));
        fileMenu.add(createMenuItem("Abrir", KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), this::openFile));
        fileMenu.add(createMenuItem("Salvar", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), this::saveFile));
        fileMenu.add(createMenuItem("Salvar Como", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), this::saveAsFile));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Sair", KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK), this::exitApplication));
        
        // Menu Editar
        JMenu editMenu = new JMenu("Editar");
        editMenu.add(createMenuItem("Desfazer", KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), this::undo));
        editMenu.add(createMenuItem("Refazer", KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), this::redo));
        editMenu.addSeparator();
        editMenu.add(createMenuItem("Recortar", KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), this::cut));
        editMenu.add(createMenuItem("Copiar", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), this::copy));
        editMenu.add(createMenuItem("Colar", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), this::paste));
        
        // Menu Executar
        JMenu runMenu = new JMenu("Executar");
        runMenu.add(createMenuItem("Compilar", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), this::compile));
        runMenu.add(createMenuItem("Executar", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), this::run));
        runMenu.add(createMenuItem("Compilar e Executar", KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), this::compileAndRun));
        
        // Menu Ajuda
        JMenu helpMenu = new JMenu("Ajuda");
        helpMenu.add(createMenuItem("Sobre", null, this::showAbout));
        helpMenu.add(createMenuItem("Sintaxe EduScript", KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), this::showSyntaxHelp));
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(runMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private JMenuItem createMenuItem(String text, KeyStroke keyStroke, Runnable action) {
        JMenuItem item = new JMenuItem(text);
        if (keyStroke != null) {
            item.setAccelerator(keyStroke);
        }
        item.addActionListener(e -> action.run());
        return item;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Painel principal com divisor
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setTopComponent(new JScrollPane(editorPane));
        mainSplitPane.setBottomComponent(bottomPane);
        mainSplitPane.setDividerLocation(400);
        mainSplitPane.setResizeWeight(0.7);
        
        // Toolbar
        JToolBar toolBar = createToolBar();
        
        add(toolBar, BorderLayout.NORTH);
        add(mainSplitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        toolBar.add(createButton("Novo", "Criar novo arquivo", this::newFile));
        toolBar.add(createButton("Abrir", "Abrir arquivo", this::openFile));
        toolBar.add(createButton("Salvar", "Salvar arquivo", this::saveFile));
        toolBar.addSeparator();
        toolBar.add(createButton("Compilar", "Compilar código", this::compile));
        toolBar.add(createButton("Executar", "Executar programa", this::run));
        toolBar.addSeparator();
        toolBar.add(createButton("Desfazer", "Desfazer última ação", this::undo));
        toolBar.add(createButton("Refazer", "Refazer ação", this::redo));
        
        return toolBar;
    }
    
    private JButton createButton(String text, String tooltip, Runnable action) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        button.addActionListener(e -> action.run());
        return button;
    }
    
    private void setupSyntaxHighlighting() {
        // Adiciona listener para destaque em tempo real
        editorPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applySyntaxHighlighting());
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applySyntaxHighlighting());
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> applySyntaxHighlighting());
            }
        });
    }
    
    private void applySyntaxHighlighting() {
        StyledDocument doc = editorPane.getStyledDocument();
        String text = editorPane.getText();
        
        // Remove formatação anterior
        doc.setCharacterAttributes(0, text.length(), new SimpleAttributeSet(), true);
        
        // Estilo padrão
        SimpleAttributeSet defaultStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(defaultStyle, Color.BLACK);
        
        // Destaca palavras-chave
        for (String keyword : KEYWORDS) {
            highlightPattern(doc, text, "\\b" + keyword + "\\b", KEYWORD_COLOR, true);
        }
        
        // Destaca strings
        highlightPattern(doc, text, "\"[^\"]*\"", STRING_COLOR, false);
        
        // Destaca comentários
        highlightPattern(doc, text, "//.*", COMMENT_COLOR, true);
        
        // Destaca números
        highlightPattern(doc, text, "\\b\\d+(\\.\\d+)?\\b", NUMBER_COLOR, false);
        
        // Destaca operadores
        highlightPattern(doc, text, "[+\\-*/=<>!]=?|[(){}\\[\\];,]", OPERATOR_COLOR, false);
    }
    
    private void highlightPattern(StyledDocument doc, String text, String pattern, Color color, boolean bold) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        
        while (m.find()) {
            SimpleAttributeSet style = new SimpleAttributeSet();
            StyleConstants.setForeground(style, color);
            if (bold) {
                StyleConstants.setBold(style, true);
            }
            doc.setCharacterAttributes(m.start(), m.end() - m.start(), style, false);
        }
    }
    
    private void setupEventHandlers() {
        // Adiciona atalhos de teclado personalizados
        InputMap inputMap = editorPane.getInputMap();
        ActionMap actionMap = editorPane.getActionMap();
        
        // Tab para indentação
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "indent");
        actionMap.put("indent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editorPane.getDocument().insertString(editorPane.getCaretPosition(), "    ", null);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    // Ações dos menus
    private void newFile() {
        if (confirmSaveChanges()) {
            editorPane.setText("");
            currentFile = null;
            setTitle("EduScript IDE - Novo Arquivo");
            updateStatus("Novo arquivo criado");
        }
    }
    
    private void openFile() {
        if (confirmSaveChanges()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos EduScript (*.eds)", "eds"));
            
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    currentFile = fileChooser.getSelectedFile();
                    String content = readFile(currentFile);
                    editorPane.setText(content);
                    setTitle("EduScript IDE - " + currentFile.getName());
                    updateStatus("Arquivo aberto: " + currentFile.getName());
                    applySyntaxHighlighting();
                } catch (IOException e) {
                    showError("Erro ao abrir arquivo: " + e.getMessage());
                }
            }
        }
    }
    
    private void saveFile() {
        if (currentFile != null) {
            try {
                writeFile(currentFile, editorPane.getText());
                updateStatus("Arquivo salvo: " + currentFile.getName());
            } catch (IOException e) {
                showError("Erro ao salvar arquivo: " + e.getMessage());
            }
        } else {
            saveAsFile();
        }
    }
    
    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos EduScript (*.eds)", "eds"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                currentFile = fileChooser.getSelectedFile();
                if (!currentFile.getName().endsWith(".eds")) {
                    currentFile = new File(currentFile.getAbsolutePath() + ".eds");
                }
                writeFile(currentFile, editorPane.getText());
                setTitle("EduScript IDE - " + currentFile.getName());
                updateStatus("Arquivo salvo como: " + currentFile.getName());
            } catch (IOException e) {
                showError("Erro ao salvar arquivo: " + e.getMessage());
            }
        }
    }
    
    private void exitApplication() {
        if (confirmSaveChanges()) {
            System.exit(0);
        }
    }
    
    private void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }
    
    private void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }
    
    private void cut() {
        editorPane.cut();
    }
    
    private void copy() {
        editorPane.copy();
    }
    
    private void paste() {
        editorPane.paste();
    }
    
    private void compile() {
        updateStatus("Compilando...");
        errorArea.setText("");
        
        try {
            // Simulação da compilação
            String code = editorPane.getText();
            if (validateSyntax(code)) {
                outputArea.setText("Compilação realizada com sucesso!\n");
                updateStatus("Compilação concluída");
                bottomPane.setSelectedIndex(0); // Mostra aba de saída
            }
        } catch (Exception e) {
            errorArea.setText("Erro de compilação:\n" + e.getMessage());
            updateStatus("Erro na compilação");
            bottomPane.setSelectedIndex(1); // Mostra aba de erros
        }
    }
    
    private void run() {
        updateStatus("Executando...");
        outputArea.setText("Executando programa EduScript...\n");
        outputArea.append("(Funcionalidade de execução será implementada com ANTLR)\n");
        bottomPane.setSelectedIndex(0);
        updateStatus("Execução simulada");
    }
    
    private void compileAndRun() {
        compile();
        if (errorArea.getText().isEmpty()) {
            run();
        }
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(this,
            "EduScript IDE v1.0\n\n" +
            "IDE para a linguagem educacional EduScript\n" +
            "Desenvolvido para o curso de Compiladores\n" +
            "Faculdade Engenheiro Salvador Arena\n\n" +
            "Grupo 9 - 2025",
            "Sobre o EduScript IDE",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showSyntaxHelp() {
        String help = """
            SINTAXE EDUSCRIPT - GRUPO 9
            
            Estrutura do Programa:
            programa NomePrograma;
            var variavel : tipo;
            inicio
                comandos...
            fimprograma
            
            Tipos de Dados:
            - inteiro: números inteiros
            - real: números decimais
            
            Comandos:
            - Atribuição: variavel = expressao;
            - Leitura: ler(variavel);
            - Escrita: escrever(expressao);
            - While: enquanto condicao faca comandos fimenquanto
            
            Expressões:
            - Operadores: +, -, *, /
            - Parênteses para precedência
            - Variáveis e números
            
            Comentários:
            // comentário de uma linha
            
            Exemplo:
            programa Exemplo;
            var x : real;
            inicio
                ler(x);
                enquanto x > 0 faca
                    escrever(x);
                    x = x - 1;
                fimenquanto
            fimprograma
            """;
        
        JTextArea textArea = new JTextArea(help);
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Sintaxe EduScript", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Métodos auxiliares
    private boolean confirmSaveChanges() {
        // Implementar verificação de mudanças pendentes
        return true;
    }
    
    private String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    private void writeFile(File file, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }
    
    private boolean validateSyntax(String code) throws Exception {
        // Validação básica da sintaxe
        if (!code.trim().startsWith("programa")) {
            throw new Exception("Programa deve começar com 'programa'");
        }
        if (!code.trim().endsWith("fimprograma")) {
            throw new Exception("Programa deve terminar com 'fimprograma'");
        }
        return true;
    }
    
    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    // Método main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Se não conseguir definir o look and feel do sistema, usa o padrão
                System.out.println("Usando look and feel padrão");
            }
            
            new EduScriptIDE().setVisible(true);
        });
    }
}
