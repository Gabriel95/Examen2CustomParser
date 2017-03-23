import tree.expression.*;
import tree.statement.*;
import com.google.common.collect.RowSortedTable;
import com.google.common.collect.TreeBasedTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class parser {

    private class GrammarLine {
        public String Producer;
        public List<String> Productions;

        public GrammarLine(String producer, List<String> productions) {
            Producer = producer;
            Productions = productions;
        }
    }

    public class ParserException extends Exception
    {
        public ParserException(String message) {
            super(message);
        }
    }
    private RowSortedTable<String, String, String> table;
    private List<GrammarLine> grammarLines;
    private Stack stack;
    private Lexer lexer;
    private boolean next = false;
    public parser(Lexer lexer)
    {
        table = TreeBasedTable.create();
        grammarLines = new ArrayList<>();
        stack = new Stack();
        this.lexer = lexer;
		table.put("0", "$", "r1");
		table.put("0", "GOTO", "d5");
		table.put("0", "ID", "d4");
		table.put("0", "IF", "d7");
		table.put("0", "PRINT", "d9");
		table.put("0", "READ", "d8");
		table.put("0", "label", "6");
		table.put("0", "statement", "2");
		table.put("0", "statementList", "1");
		table.put("0", "var", "3");
		table.put("1", "$", "Accepted");
		table.put("10", "$", "r2");
		table.put("11", "ID", "d18");
		table.put("11", "NUM", "d20");
		table.put("11", "const", "19");
		table.put("11", "expr", "23");
		table.put("11", "var", "17");
		table.put("11", "varConst", "24");
		table.put("12", "$", "r4");
		table.put("12", "GOTO", "r4");
		table.put("12", "ID", "r4");
		table.put("12", "IF", "r4");
		table.put("12", "PRINT", "r4");
		table.put("12", "READ", "r4");
		table.put("13", "$", "r23");
		table.put("13", "GOTO", "r23");
		table.put("13", "ID", "r23");
		table.put("13", "IF", "r23");
		table.put("13", "PRINT", "r23");
		table.put("13", "READ", "r23");
		table.put("14", "$", "r5");
		table.put("14", "GOTO", "r5");
		table.put("14", "ID", "r5");
		table.put("14", "IF", "r5");
		table.put("14", "PRINT", "r5");
		table.put("14", "READ", "r5");
		table.put("15", "GOTO", "d25");
		table.put("16", "EQ", "d26");
		table.put("16", "GE", "d30");
		table.put("16", "GT", "d28");
		table.put("16", "LE", "d31");
		table.put("16", "LT", "d29");
		table.put("16", "NEQ", "d27");
		table.put("17", "$", "r20");
		table.put("17", "DIV", "r20");
		table.put("17", "EQ", "r20");
		table.put("17", "GE", "r20");
		table.put("17", "GOTO", "r20");
		table.put("17", "GT", "r20");
		table.put("17", "ID", "r20");
		table.put("17", "IF", "r20");
		table.put("17", "LE", "r20");
		table.put("17", "LT", "r20");
		table.put("17", "MINUS", "r20");
		table.put("17", "MUL", "r20");
		table.put("17", "NEQ", "r20");
		table.put("17", "PLUS", "r20");
		table.put("17", "PRINT", "r20");
		table.put("17", "READ", "r20");
		table.put("18", "$", "r22");
		table.put("18", "DIV", "r22");
		table.put("18", "EQ", "r22");
		table.put("18", "GE", "r22");
		table.put("18", "GOTO", "r22");
		table.put("18", "GT", "r22");
		table.put("18", "ID", "r22");
		table.put("18", "IF", "r22");
		table.put("18", "LE", "r22");
		table.put("18", "LT", "r22");
		table.put("18", "MINUS", "r22");
		table.put("18", "MUL", "r22");
		table.put("18", "NEQ", "r22");
		table.put("18", "PLUS", "r22");
		table.put("18", "PRINT", "r22");
		table.put("18", "READ", "r22");
		table.put("19", "$", "r21");
		table.put("19", "DIV", "r21");
		table.put("19", "EQ", "r21");
		table.put("19", "GE", "r21");
		table.put("19", "GOTO", "r21");
		table.put("19", "GT", "r21");
		table.put("19", "ID", "r21");
		table.put("19", "IF", "r21");
		table.put("19", "LE", "r21");
		table.put("19", "LT", "r21");
		table.put("19", "MINUS", "r21");
		table.put("19", "MUL", "r21");
		table.put("19", "NEQ", "r21");
		table.put("19", "PLUS", "r21");
		table.put("19", "PRINT", "r21");
		table.put("19", "READ", "r21");
		table.put("2", "$", "r1");
		table.put("2", "GOTO", "d5");
		table.put("2", "ID", "d4");
		table.put("2", "IF", "d7");
		table.put("2", "PRINT", "d9");
		table.put("2", "READ", "d8");
		table.put("2", "label", "6");
		table.put("2", "statement", "2");
		table.put("2", "statementList", "10");
		table.put("2", "var", "3");
		table.put("20", "$", "r24");
		table.put("20", "DIV", "r24");
		table.put("20", "EQ", "r24");
		table.put("20", "GE", "r24");
		table.put("20", "GOTO", "r24");
		table.put("20", "GT", "r24");
		table.put("20", "ID", "r24");
		table.put("20", "IF", "r24");
		table.put("20", "LE", "r24");
		table.put("20", "LT", "r24");
		table.put("20", "MINUS", "r24");
		table.put("20", "MUL", "r24");
		table.put("20", "NEQ", "r24");
		table.put("20", "PLUS", "r24");
		table.put("20", "PRINT", "r24");
		table.put("20", "READ", "r24");
		table.put("21", "$", "r7");
		table.put("21", "GOTO", "r7");
		table.put("21", "ID", "r7");
		table.put("21", "IF", "r7");
		table.put("21", "PRINT", "r7");
		table.put("21", "READ", "r7");
		table.put("22", "$", "r8");
		table.put("22", "GOTO", "r8");
		table.put("22", "ID", "r8");
		table.put("22", "IF", "r8");
		table.put("22", "PRINT", "r8");
		table.put("22", "READ", "r8");
		table.put("23", "$", "r3");
		table.put("23", "GOTO", "r3");
		table.put("23", "ID", "r3");
		table.put("23", "IF", "r3");
		table.put("23", "PRINT", "r3");
		table.put("23", "READ", "r3");
		table.put("24", "$", "r9");
		table.put("24", "DIV", "d35");
		table.put("24", "GOTO", "r9");
		table.put("24", "ID", "r9");
		table.put("24", "IF", "r9");
		table.put("24", "MINUS", "d33");
		table.put("24", "MUL", "d34");
		table.put("24", "PLUS", "d32");
		table.put("24", "PRINT", "r9");
		table.put("24", "READ", "r9");
		table.put("25", "ID", "d13");
		table.put("25", "label", "36");
		table.put("26", "ID", "d18");
		table.put("26", "NUM", "d20");
		table.put("26", "const", "19");
		table.put("26", "var", "17");
		table.put("26", "varConst", "37");
		table.put("27", "ID", "d18");
		table.put("27", "NUM", "d20");
		table.put("27", "const", "19");
		table.put("27", "var", "17");
		table.put("27", "varConst", "38");
		table.put("28", "ID", "d18");
		table.put("28", "NUM", "d20");
		table.put("28", "const", "19");
		table.put("28", "var", "17");
		table.put("28", "varConst", "39");
		table.put("29", "ID", "d18");
		table.put("29", "NUM", "d20");
		table.put("29", "const", "19");
		table.put("29", "var", "17");
		table.put("29", "varConst", "40");
		table.put("3", "EQUAL", "d11");
		table.put("30", "ID", "d18");
		table.put("30", "NUM", "d20");
		table.put("30", "const", "19");
		table.put("30", "var", "17");
		table.put("30", "varConst", "41");
		table.put("31", "ID", "d18");
		table.put("31", "NUM", "d20");
		table.put("31", "const", "19");
		table.put("31", "var", "17");
		table.put("31", "varConst", "42");
		table.put("32", "ID", "d18");
		table.put("32", "NUM", "d20");
		table.put("32", "const", "19");
		table.put("32", "var", "17");
		table.put("32", "varConst", "43");
		table.put("33", "ID", "d18");
		table.put("33", "NUM", "d20");
		table.put("33", "const", "19");
		table.put("33", "var", "17");
		table.put("33", "varConst", "44");
		table.put("34", "ID", "d18");
		table.put("34", "NUM", "d20");
		table.put("34", "const", "19");
		table.put("34", "var", "17");
		table.put("34", "varConst", "45");
		table.put("35", "ID", "d18");
		table.put("35", "NUM", "d20");
		table.put("35", "const", "19");
		table.put("35", "var", "17");
		table.put("35", "varConst", "46");
		table.put("36", "$", "r6");
		table.put("36", "GOTO", "r6");
		table.put("36", "ID", "r6");
		table.put("36", "IF", "r6");
		table.put("36", "PRINT", "r6");
		table.put("36", "READ", "r6");
		table.put("37", "GOTO", "r14");
		table.put("38", "GOTO", "r15");
		table.put("39", "GOTO", "r16");
		table.put("4", "COLON", "r23");
		table.put("4", "EQUAL", "r22");
		table.put("40", "GOTO", "r17");
		table.put("41", "GOTO", "r18");
		table.put("42", "GOTO", "r19");
		table.put("43", "$", "r10");
		table.put("43", "GOTO", "r10");
		table.put("43", "ID", "r10");
		table.put("43", "IF", "r10");
		table.put("43", "PRINT", "r10");
		table.put("43", "READ", "r10");
		table.put("44", "$", "r11");
		table.put("44", "GOTO", "r11");
		table.put("44", "ID", "r11");
		table.put("44", "IF", "r11");
		table.put("44", "PRINT", "r11");
		table.put("44", "READ", "r11");
		table.put("45", "$", "r12");
		table.put("45", "GOTO", "r12");
		table.put("45", "ID", "r12");
		table.put("45", "IF", "r12");
		table.put("45", "PRINT", "r12");
		table.put("45", "READ", "r12");
		table.put("46", "$", "r13");
		table.put("46", "GOTO", "r13");
		table.put("46", "ID", "r13");
		table.put("46", "IF", "r13");
		table.put("46", "PRINT", "r13");
		table.put("46", "READ", "r13");
		table.put("5", "ID", "d13");
		table.put("5", "label", "12");
		table.put("6", "COLON", "d14");
		table.put("7", "ID", "d18");
		table.put("7", "NUM", "d20");
		table.put("7", "boolExpr", "15");
		table.put("7", "const", "19");
		table.put("7", "var", "17");
		table.put("7", "varConst", "16");
		table.put("8", "ID", "d18");
		table.put("8", "var", "21");
		table.put("9", "ID", "d18");
		table.put("9", "var", "22");
		grammarLines.add(new GrammarLine("statementList",new ArrayList<>(Arrays.asList("ɛ"))));
		grammarLines.add(new GrammarLine("statementList",new ArrayList<>(Arrays.asList("statement", "statementList"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("var", "EQUAL", "expr"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("GOTO", "label"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("label", "COLON"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("IF", "boolExpr", "GOTO", "label"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("READ", "var"))));
		grammarLines.add(new GrammarLine("statement",new ArrayList<>(Arrays.asList("PRINT", "var"))));
		grammarLines.add(new GrammarLine("expr",new ArrayList<>(Arrays.asList("varConst"))));
		grammarLines.add(new GrammarLine("expr",new ArrayList<>(Arrays.asList("varConst", "PLUS", "varConst"))));
		grammarLines.add(new GrammarLine("expr",new ArrayList<>(Arrays.asList("varConst", "MINUS", "varConst"))));
		grammarLines.add(new GrammarLine("expr",new ArrayList<>(Arrays.asList("varConst", "MUL", "varConst"))));
		grammarLines.add(new GrammarLine("expr",new ArrayList<>(Arrays.asList("varConst", "DIV", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "EQ", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "NEQ", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "GT", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "LT", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "GE", "varConst"))));
		grammarLines.add(new GrammarLine("boolExpr",new ArrayList<>(Arrays.asList("varConst", "LE", "varConst"))));
		grammarLines.add(new GrammarLine("varConst",new ArrayList<>(Arrays.asList("var"))));
		grammarLines.add(new GrammarLine("varConst",new ArrayList<>(Arrays.asList("const"))));
		grammarLines.add(new GrammarLine("var",new ArrayList<>(Arrays.asList("ID"))));
		grammarLines.add(new GrammarLine("label",new ArrayList<>(Arrays.asList("ID"))));
		grammarLines.add(new GrammarLine("const",new ArrayList<>(Arrays.asList("NUM"))));
 }

    public Object parse() throws ParserException {
        stack.push("0");
        try
        {
            Symbol s = lexer.yylex();
            while(s != null)
            {
                if(!table.contains(stack.peek(),sym.terminalNames[s.getType()]))
                    throw new ParserException("Unexpected token at line: " + s.getYyline() + " column: " + s.getYycolumn());
                String action = table.get(stack.peek(),sym.terminalNames[s.getType()]);
                doAction(action,s.getValue());
                if(stack.peek().equals("Accepted"))
                {
                    return stack.elementAt(stack.size()-3);
                }
                if(next)
                    s = lexer.yylex();
            }
            throw new ParserException("Expected EOF");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new ParserException("Expected EOF");
    }

    private void doAction(String action, Object value) throws ParserException {
        char a = action.charAt(0);
        if(action.equals("Accepted"))
        {
            stack.push(action);
            next = true;
            return;
        }
        switch (a)
        {
            case 'd':
                stack.push(value);
                stack.push(action.replace("d",""));
                next = true;
                break;
            case 'r':
                int r = Integer.parseInt(action.replace("r",""));
                GrammarLine grammarLine = grammarLines.get(r - 1);
                int magnitude = 0;
                if(grammarLine.Productions.size() > 1)
                    magnitude = grammarLine.Productions.size();
                else if(!grammarLine.Productions.get(0).equals("ɛ"))
                    magnitude = 1;
                DoReduction(r, magnitude);
                if(!table.contains(stack.elementAt(stack.size()-2),grammarLine.Producer))
                    throw new ParserException("Unexpected token");
                stack.push(table.get(stack.elementAt(stack.size()-2),grammarLine.Producer));
                next = false;
                break;

        }
    }

    private void PopStack(int magnitude)
    {
        for(int i = 0; i < magnitude * 2; i++)
        {
            stack.pop();
        }
    }

    private void DoReduction(int r, int magnitude)
    {
        Object RESULT = null;
        switch (r)
        {
			case 1:
			{
				PopStack(magnitude);
				List<StatementNode> nodeList = new ArrayList<StatementNode>(); RESULT = nodeList; 
				stack.push(RESULT);
				return;
			}
			case 2:
			{
				StatementNode s;
				List<StatementNode> sL;
				s = (StatementNode) stack.elementAt(stack.size() - 4);
				sL = (List<StatementNode>) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 sL.add(0,s); RESULT = sL; 
				stack.push(RESULT);
				return;
			}
			case 3:
			{
				IdNode v;
				ExpressionNode e;
				v = (IdNode) stack.elementAt(stack.size() - 6);
				e = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new AssignNode(e,v); 
				stack.push(RESULT);
				return;
			}
			case 4:
			{
				IdNode l;
				l = (IdNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new GoToLabel(l); 
				stack.push(RESULT);
				return;
			}
			case 5:
			{
				IdNode l;
				l = (IdNode) stack.elementAt(stack.size() - 4);
				PopStack(magnitude);
				 RESULT = new LabelNode(l); 
				stack.push(RESULT);
				return;
			}
			case 6:
			{
				ExpressionNode b;
				IdNode l;
				b = (ExpressionNode) stack.elementAt(stack.size() - 6);
				l = (IdNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new IfNode(b,l); 
				stack.push(RESULT);
				return;
			}
			case 7:
			{
				IdNode v;
				v = (IdNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new ScanNode(v); 
				stack.push(RESULT);
				return;
			}
			case 8:
			{
				IdNode v;
				v = (IdNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new PrintNode(v); 
				stack.push(RESULT);
				return;
			}
			case 9:
			{
				ExpressionNode vC;
				vC = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = vC; 
				stack.push(RESULT);
				return;
			}
			case 10:
			{
				ExpressionNode vC1;
				ExpressionNode vC2;
				vC1 = (ExpressionNode) stack.elementAt(stack.size() - 6);
				vC2 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new SumNode(vC2,vC1); 
				stack.push(RESULT);
				return;
			}
			case 11:
			{
				ExpressionNode vC1;
				ExpressionNode vC2;
				vC1 = (ExpressionNode) stack.elementAt(stack.size() - 6);
				vC2 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new SubNode(vC2,vC1); 
				stack.push(RESULT);
				return;
			}
			case 12:
			{
				ExpressionNode vC1;
				ExpressionNode vC2;
				vC1 = (ExpressionNode) stack.elementAt(stack.size() - 6);
				vC2 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new MultNode(vC2,vC1); 
				stack.push(RESULT);
				return;
			}
			case 13:
			{
				ExpressionNode vC1;
				ExpressionNode vC2;
				vC1 = (ExpressionNode) stack.elementAt(stack.size() - 6);
				vC2 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new DivNode(vC2,vC1); 
				stack.push(RESULT);
				return;
			}
			case 14:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new EqualsNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 15:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new NotEqualsNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 16:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new GreaterThanNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 17:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new LessThanNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 18:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new GreaterOrEqualsThanNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 19:
			{
				ExpressionNode l;
				ExpressionNode r1;
				l = (ExpressionNode) stack.elementAt(stack.size() - 6);
				r1 = (ExpressionNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new LessOrEqualsThanNode(r1,l); 
				stack.push(RESULT);
				return;
			}
			case 20:
			{
				IdNode v;
				v = (IdNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = v; 
				stack.push(RESULT);
				return;
			}
			case 21:
			{
				NumberNode c;
				c = (NumberNode) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = c; 
				stack.push(RESULT);
				return;
			}
			case 22:
			{
				String id;
				id = (String) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new IdNode(id); 
				stack.push(RESULT);
				return;
			}
			case 23:
			{
				String id;
				id = (String) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new IdNode(id); 
				stack.push(RESULT);
				return;
			}
			case 24:
			{
				Integer n;
				n = (Integer) stack.elementAt(stack.size() - 2);
				PopStack(magnitude);
				 RESULT = new NumberNode(n); 
				stack.push(RESULT);
				return;
			}
			default:
				return;
		}
	}
}