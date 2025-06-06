// Generated from EasyLanguage.g4 by ANTLR 4.12.0
package br.edu.cefsa.compiler.parser;

	import br.edu.cefsa.compiler.datastructures.EasySymbol;
	import br.edu.cefsa.compiler.datastructures.EasyVariable;
	import br.edu.cefsa.compiler.datastructures.EasyFunction;
	import br.edu.cefsa.compiler.datastructures.EasySymbolTable;
	import br.edu.cefsa.compiler.exceptions.EasySemanticException;
	import br.edu.cefsa.compiler.abstractsyntaxtree.*;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class EasyLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, AP=30, FP=31, SC=32, 
		OP=33, ATTR=34, VIR=35, ACH=36, FCH=37, OPREL=38, ID=39, NUMBER=40, STRING=41, 
		BOOLEAN=42, CHAR=43, COMMENT_LINE=44, COMMENT_BLOCK=45, WS=46;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "AP", "FP", "SC", "OP", "ATTR", "VIR", 
			"ACH", "FCH", "OPREL", "ID", "NUMBER", "STRING", "BOOLEAN", "CHAR", "COMMENT_LINE", 
			"COMMENT_BLOCK", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim;'", "'const'", "'vazio'", "'funcao'", 
			"'fim'", "'inteiro'", "'real'", "'texto'", "'caractere'", "'logico'", 
			"'vetor'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
			"'faca'", "'para'", "'ate'", "'passo'", "'retorna'", "'e'", "'ou'", "'*'", 
			"'/'", "'%'", "'('", "')'", "';'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "AP", "FP", "SC", "OP", "ATTR", "VIR", 
			"ACH", "FCH", "OPREL", "ID", "NUMBER", "STRING", "BOOLEAN", "CHAR", "COMMENT_LINE", 
			"COMMENT_BLOCK", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private int _tipo;
		private String _varName;
		private String _varValue;
		private EasySymbolTable symbolTable = new EasySymbolTable();
		private EasySymbol symbol;
		private EasyProgram program = new EasyProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> listaWhile;
		private ArrayList<AbstractCommand> listaFor;
		private String _functionName;
		private ArrayList<EasyVariable> _parameters;
		private int _returnType;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new EasySemanticException("Symbol "+id+" not declared");
			}
		}
		
		public void verificaTipo(String id, int expectedType){
			if (!symbolTable.exists(id)){
				throw new EasySemanticException("Symbol "+id+" not declared");
			}
			EasyVariable var = (EasyVariable)symbolTable.get(id);
			if (var.getType() != expectedType){
				throw new EasySemanticException("Type mismatch for symbol "+id);
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}


	public EasyLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EasyLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000.\u016c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001"+
		"!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u0119\b%\u0001&\u0001"+
		"&\u0005&\u011d\b&\n&\f&\u0120\t&\u0001\'\u0004\'\u0123\b\'\u000b\'\f\'"+
		"\u0124\u0001\'\u0001\'\u0004\'\u0129\b\'\u000b\'\f\'\u012a\u0003\'\u012d"+
		"\b\'\u0001(\u0001(\u0005(\u0131\b(\n(\f(\u0134\t(\u0001(\u0001(\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0003)\u0147\b)\u0001*\u0001*\u0001*\u0001"+
		"*\u0001+\u0001+\u0001+\u0001+\u0005+\u0151\b+\n+\f+\u0154\t+\u0001+\u0001"+
		"+\u0001,\u0001,\u0001,\u0001,\u0005,\u015c\b,\n,\f,\u015f\t,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001-\u0004-\u0167\b-\u000b-\f-\u0168\u0001-\u0001"+
		"-\u0001\u015d\u0000.\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t"+
		"\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f"+
		"\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014"+
		")\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e"+
		"=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.\u0001\u0000\b\u0002\u0000++--"+
		"\u0002\u0000<<>>\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001\u000009\u0003"+
		"\u0000\n\n\r\r\"\"\u0002\u0000\n\n\r\r\u0003\u0000\t\n\r\r  \u0178\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000"+
		"\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000"+
		"\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K"+
		"\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000"+
		"\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y"+
		"\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0001]\u0001\u0000"+
		"\u0000\u0000\u0003f\u0001\u0000\u0000\u0000\u0005m\u0001\u0000\u0000\u0000"+
		"\u0007r\u0001\u0000\u0000\u0000\tx\u0001\u0000\u0000\u0000\u000b~\u0001"+
		"\u0000\u0000\u0000\r\u0085\u0001\u0000\u0000\u0000\u000f\u0089\u0001\u0000"+
		"\u0000\u0000\u0011\u0091\u0001\u0000\u0000\u0000\u0013\u0096\u0001\u0000"+
		"\u0000\u0000\u0015\u009c\u0001\u0000\u0000\u0000\u0017\u00a6\u0001\u0000"+
		"\u0000\u0000\u0019\u00ad\u0001\u0000\u0000\u0000\u001b\u00b3\u0001\u0000"+
		"\u0000\u0000\u001d\u00b8\u0001\u0000\u0000\u0000\u001f\u00c0\u0001\u0000"+
		"\u0000\u0000!\u00c3\u0001\u0000\u0000\u0000#\u00c9\u0001\u0000\u0000\u0000"+
		"%\u00cf\u0001\u0000\u0000\u0000\'\u00d8\u0001\u0000\u0000\u0000)\u00dd"+
		"\u0001\u0000\u0000\u0000+\u00e2\u0001\u0000\u0000\u0000-\u00e6\u0001\u0000"+
		"\u0000\u0000/\u00ec\u0001\u0000\u0000\u00001\u00f4\u0001\u0000\u0000\u0000"+
		"3\u00f6\u0001\u0000\u0000\u00005\u00f9\u0001\u0000\u0000\u00007\u00fb"+
		"\u0001\u0000\u0000\u00009\u00fd\u0001\u0000\u0000\u0000;\u00ff\u0001\u0000"+
		"\u0000\u0000=\u0101\u0001\u0000\u0000\u0000?\u0103\u0001\u0000\u0000\u0000"+
		"A\u0105\u0001\u0000\u0000\u0000C\u0107\u0001\u0000\u0000\u0000E\u0109"+
		"\u0001\u0000\u0000\u0000G\u010b\u0001\u0000\u0000\u0000I\u010d\u0001\u0000"+
		"\u0000\u0000K\u0118\u0001\u0000\u0000\u0000M\u011a\u0001\u0000\u0000\u0000"+
		"O\u0122\u0001\u0000\u0000\u0000Q\u012e\u0001\u0000\u0000\u0000S\u0146"+
		"\u0001\u0000\u0000\u0000U\u0148\u0001\u0000\u0000\u0000W\u014c\u0001\u0000"+
		"\u0000\u0000Y\u0157\u0001\u0000\u0000\u0000[\u0166\u0001\u0000\u0000\u0000"+
		"]^\u0005p\u0000\u0000^_\u0005r\u0000\u0000_`\u0005o\u0000\u0000`a\u0005"+
		"g\u0000\u0000ab\u0005r\u0000\u0000bc\u0005a\u0000\u0000cd\u0005m\u0000"+
		"\u0000de\u0005a\u0000\u0000e\u0002\u0001\u0000\u0000\u0000fg\u0005i\u0000"+
		"\u0000gh\u0005n\u0000\u0000hi\u0005i\u0000\u0000ij\u0005c\u0000\u0000"+
		"jk\u0005i\u0000\u0000kl\u0005o\u0000\u0000l\u0004\u0001\u0000\u0000\u0000"+
		"mn\u0005f\u0000\u0000no\u0005i\u0000\u0000op\u0005m\u0000\u0000pq\u0005"+
		";\u0000\u0000q\u0006\u0001\u0000\u0000\u0000rs\u0005c\u0000\u0000st\u0005"+
		"o\u0000\u0000tu\u0005n\u0000\u0000uv\u0005s\u0000\u0000vw\u0005t\u0000"+
		"\u0000w\b\u0001\u0000\u0000\u0000xy\u0005v\u0000\u0000yz\u0005a\u0000"+
		"\u0000z{\u0005z\u0000\u0000{|\u0005i\u0000\u0000|}\u0005o\u0000\u0000"+
		"}\n\u0001\u0000\u0000\u0000~\u007f\u0005f\u0000\u0000\u007f\u0080\u0005"+
		"u\u0000\u0000\u0080\u0081\u0005n\u0000\u0000\u0081\u0082\u0005c\u0000"+
		"\u0000\u0082\u0083\u0005a\u0000\u0000\u0083\u0084\u0005o\u0000\u0000\u0084"+
		"\f\u0001\u0000\u0000\u0000\u0085\u0086\u0005f\u0000\u0000\u0086\u0087"+
		"\u0005i\u0000\u0000\u0087\u0088\u0005m\u0000\u0000\u0088\u000e\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005i\u0000\u0000\u008a\u008b\u0005n\u0000\u0000"+
		"\u008b\u008c\u0005t\u0000\u0000\u008c\u008d\u0005e\u0000\u0000\u008d\u008e"+
		"\u0005i\u0000\u0000\u008e\u008f\u0005r\u0000\u0000\u008f\u0090\u0005o"+
		"\u0000\u0000\u0090\u0010\u0001\u0000\u0000\u0000\u0091\u0092\u0005r\u0000"+
		"\u0000\u0092\u0093\u0005e\u0000\u0000\u0093\u0094\u0005a\u0000\u0000\u0094"+
		"\u0095\u0005l\u0000\u0000\u0095\u0012\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0005t\u0000\u0000\u0097\u0098\u0005e\u0000\u0000\u0098\u0099\u0005x"+
		"\u0000\u0000\u0099\u009a\u0005t\u0000\u0000\u009a\u009b\u0005o\u0000\u0000"+
		"\u009b\u0014\u0001\u0000\u0000\u0000\u009c\u009d\u0005c\u0000\u0000\u009d"+
		"\u009e\u0005a\u0000\u0000\u009e\u009f\u0005r\u0000\u0000\u009f\u00a0\u0005"+
		"a\u0000\u0000\u00a0\u00a1\u0005c\u0000\u0000\u00a1\u00a2\u0005t\u0000"+
		"\u0000\u00a2\u00a3\u0005e\u0000\u0000\u00a3\u00a4\u0005r\u0000\u0000\u00a4"+
		"\u00a5\u0005e\u0000\u0000\u00a5\u0016\u0001\u0000\u0000\u0000\u00a6\u00a7"+
		"\u0005l\u0000\u0000\u00a7\u00a8\u0005o\u0000\u0000\u00a8\u00a9\u0005g"+
		"\u0000\u0000\u00a9\u00aa\u0005i\u0000\u0000\u00aa\u00ab\u0005c\u0000\u0000"+
		"\u00ab\u00ac\u0005o\u0000\u0000\u00ac\u0018\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0005v\u0000\u0000\u00ae\u00af\u0005e\u0000\u0000\u00af\u00b0\u0005"+
		"t\u0000\u0000\u00b0\u00b1\u0005o\u0000\u0000\u00b1\u00b2\u0005r\u0000"+
		"\u0000\u00b2\u001a\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005l\u0000\u0000"+
		"\u00b4\u00b5\u0005e\u0000\u0000\u00b5\u00b6\u0005i\u0000\u0000\u00b6\u00b7"+
		"\u0005a\u0000\u0000\u00b7\u001c\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005"+
		"e\u0000\u0000\u00b9\u00ba\u0005s\u0000\u0000\u00ba\u00bb\u0005c\u0000"+
		"\u0000\u00bb\u00bc\u0005r\u0000\u0000\u00bc\u00bd\u0005e\u0000\u0000\u00bd"+
		"\u00be\u0005v\u0000\u0000\u00be\u00bf\u0005a\u0000\u0000\u00bf\u001e\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c1\u0005s\u0000\u0000\u00c1\u00c2\u0005e\u0000"+
		"\u0000\u00c2 \u0001\u0000\u0000\u0000\u00c3\u00c4\u0005e\u0000\u0000\u00c4"+
		"\u00c5\u0005n\u0000\u0000\u00c5\u00c6\u0005t\u0000\u0000\u00c6\u00c7\u0005"+
		"a\u0000\u0000\u00c7\u00c8\u0005o\u0000\u0000\u00c8\"\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0005s\u0000\u0000\u00ca\u00cb\u0005e\u0000\u0000\u00cb"+
		"\u00cc\u0005n\u0000\u0000\u00cc\u00cd\u0005a\u0000\u0000\u00cd\u00ce\u0005"+
		"o\u0000\u0000\u00ce$\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005e\u0000"+
		"\u0000\u00d0\u00d1\u0005n\u0000\u0000\u00d1\u00d2\u0005q\u0000\u0000\u00d2"+
		"\u00d3\u0005u\u0000\u0000\u00d3\u00d4\u0005a\u0000\u0000\u00d4\u00d5\u0005"+
		"n\u0000\u0000\u00d5\u00d6\u0005t\u0000\u0000\u00d6\u00d7\u0005o\u0000"+
		"\u0000\u00d7&\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005f\u0000\u0000\u00d9"+
		"\u00da\u0005a\u0000\u0000\u00da\u00db\u0005c\u0000\u0000\u00db\u00dc\u0005"+
		"a\u0000\u0000\u00dc(\u0001\u0000\u0000\u0000\u00dd\u00de\u0005p\u0000"+
		"\u0000\u00de\u00df\u0005a\u0000\u0000\u00df\u00e0\u0005r\u0000\u0000\u00e0"+
		"\u00e1\u0005a\u0000\u0000\u00e1*\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005"+
		"a\u0000\u0000\u00e3\u00e4\u0005t\u0000\u0000\u00e4\u00e5\u0005e\u0000"+
		"\u0000\u00e5,\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005p\u0000\u0000\u00e7"+
		"\u00e8\u0005a\u0000\u0000\u00e8\u00e9\u0005s\u0000\u0000\u00e9\u00ea\u0005"+
		"s\u0000\u0000\u00ea\u00eb\u0005o\u0000\u0000\u00eb.\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0005r\u0000\u0000\u00ed\u00ee\u0005e\u0000\u0000\u00ee"+
		"\u00ef\u0005t\u0000\u0000\u00ef\u00f0\u0005o\u0000\u0000\u00f0\u00f1\u0005"+
		"r\u0000\u0000\u00f1\u00f2\u0005n\u0000\u0000\u00f2\u00f3\u0005a\u0000"+
		"\u0000\u00f30\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005e\u0000\u0000\u00f5"+
		"2\u0001\u0000\u0000\u0000\u00f6\u00f7\u0005o\u0000\u0000\u00f7\u00f8\u0005"+
		"u\u0000\u0000\u00f84\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005*\u0000"+
		"\u0000\u00fa6\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005/\u0000\u0000\u00fc"+
		"8\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005%\u0000\u0000\u00fe:\u0001"+
		"\u0000\u0000\u0000\u00ff\u0100\u0005(\u0000\u0000\u0100<\u0001\u0000\u0000"+
		"\u0000\u0101\u0102\u0005)\u0000\u0000\u0102>\u0001\u0000\u0000\u0000\u0103"+
		"\u0104\u0005;\u0000\u0000\u0104@\u0001\u0000\u0000\u0000\u0105\u0106\u0007"+
		"\u0000\u0000\u0000\u0106B\u0001\u0000\u0000\u0000\u0107\u0108\u0005=\u0000"+
		"\u0000\u0108D\u0001\u0000\u0000\u0000\u0109\u010a\u0005,\u0000\u0000\u010a"+
		"F\u0001\u0000\u0000\u0000\u010b\u010c\u0005{\u0000\u0000\u010cH\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0005}\u0000\u0000\u010eJ\u0001\u0000\u0000"+
		"\u0000\u010f\u0119\u0007\u0001\u0000\u0000\u0110\u0111\u0005>\u0000\u0000"+
		"\u0111\u0119\u0005=\u0000\u0000\u0112\u0113\u0005<\u0000\u0000\u0113\u0119"+
		"\u0005=\u0000\u0000\u0114\u0115\u0005=\u0000\u0000\u0115\u0119\u0005="+
		"\u0000\u0000\u0116\u0117\u0005!\u0000\u0000\u0117\u0119\u0005=\u0000\u0000"+
		"\u0118\u010f\u0001\u0000\u0000\u0000\u0118\u0110\u0001\u0000\u0000\u0000"+
		"\u0118\u0112\u0001\u0000\u0000\u0000\u0118\u0114\u0001\u0000\u0000\u0000"+
		"\u0118\u0116\u0001\u0000\u0000\u0000\u0119L\u0001\u0000\u0000\u0000\u011a"+
		"\u011e\u0007\u0002\u0000\u0000\u011b\u011d\u0007\u0003\u0000\u0000\u011c"+
		"\u011b\u0001\u0000\u0000\u0000\u011d\u0120\u0001\u0000\u0000\u0000\u011e"+
		"\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f"+
		"N\u0001\u0000\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0121\u0123"+
		"\u0007\u0004\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0125"+
		"\u0001\u0000\u0000\u0000\u0125\u012c\u0001\u0000\u0000\u0000\u0126\u0128"+
		"\u0005.\u0000\u0000\u0127\u0129\u0007\u0004\u0000\u0000\u0128\u0127\u0001"+
		"\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u0128\u0001"+
		"\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012d\u0001"+
		"\u0000\u0000\u0000\u012c\u0126\u0001\u0000\u0000\u0000\u012c\u012d\u0001"+
		"\u0000\u0000\u0000\u012dP\u0001\u0000\u0000\u0000\u012e\u0132\u0005\""+
		"\u0000\u0000\u012f\u0131\b\u0005\u0000\u0000\u0130\u012f\u0001\u0000\u0000"+
		"\u0000\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000"+
		"\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133\u0135\u0001\u0000\u0000"+
		"\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0136\u0005\"\u0000\u0000"+
		"\u0136R\u0001\u0000\u0000\u0000\u0137\u0138\u0005v\u0000\u0000\u0138\u0139"+
		"\u0005e\u0000\u0000\u0139\u013a\u0005r\u0000\u0000\u013a\u013b\u0005d"+
		"\u0000\u0000\u013b\u013c\u0005a\u0000\u0000\u013c\u013d\u0005d\u0000\u0000"+
		"\u013d\u013e\u0005e\u0000\u0000\u013e\u013f\u0005i\u0000\u0000\u013f\u0140"+
		"\u0005r\u0000\u0000\u0140\u0147\u0005o\u0000\u0000\u0141\u0142\u0005f"+
		"\u0000\u0000\u0142\u0143\u0005a\u0000\u0000\u0143\u0144\u0005l\u0000\u0000"+
		"\u0144\u0145\u0005s\u0000\u0000\u0145\u0147\u0005o\u0000\u0000\u0146\u0137"+
		"\u0001\u0000\u0000\u0000\u0146\u0141\u0001\u0000\u0000\u0000\u0147T\u0001"+
		"\u0000\u0000\u0000\u0148\u0149\u0005\'\u0000\u0000\u0149\u014a\t\u0000"+
		"\u0000\u0000\u014a\u014b\u0005\'\u0000\u0000\u014bV\u0001\u0000\u0000"+
		"\u0000\u014c\u014d\u0005/\u0000\u0000\u014d\u014e\u0005/\u0000\u0000\u014e"+
		"\u0152\u0001\u0000\u0000\u0000\u014f\u0151\b\u0006\u0000\u0000\u0150\u014f"+
		"\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150"+
		"\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0155"+
		"\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0155\u0156"+
		"\u0006+\u0000\u0000\u0156X\u0001\u0000\u0000\u0000\u0157\u0158\u0005/"+
		"\u0000\u0000\u0158\u0159\u0005*\u0000\u0000\u0159\u015d\u0001\u0000\u0000"+
		"\u0000\u015a\u015c\t\u0000\u0000\u0000\u015b\u015a\u0001\u0000\u0000\u0000"+
		"\u015c\u015f\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000"+
		"\u015d\u015b\u0001\u0000\u0000\u0000\u015e\u0160\u0001\u0000\u0000\u0000"+
		"\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0161\u0005*\u0000\u0000\u0161"+
		"\u0162\u0005/\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164"+
		"\u0006,\u0000\u0000\u0164Z\u0001\u0000\u0000\u0000\u0165\u0167\u0007\u0007"+
		"\u0000\u0000\u0166\u0165\u0001\u0000\u0000\u0000\u0167\u0168\u0001\u0000"+
		"\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000"+
		"\u0000\u0000\u0169\u016a\u0001\u0000\u0000\u0000\u016a\u016b\u0006-\u0000"+
		"\u0000\u016b\\\u0001\u0000\u0000\u0000\u000b\u0000\u0118\u011e\u0124\u012a"+
		"\u012c\u0132\u0146\u0152\u015d\u0168\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}