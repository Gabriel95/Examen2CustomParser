
%%
%class Lexer
%type Symbol
%line
%column

%{   
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object varue) {
        return new Symbol(type, yyline, yycolumn, varue);
    }

%}
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
dec_int_lit = 0 | [1-9][0-9]*
dec_int_id = [A-Za-z_][A-Za-z_0-9]*
%%
<YYINITIAL> {
    ":"                { return symbol(sym.COLON); }
    "+"                {  return symbol(sym.PLUS); }
    "-"                {  return symbol(sym.MINUS); }
    "*"                {  return symbol(sym.MUL); }
    "/"                {  return symbol(sym.DIV); }
    ":="               { return symbol(sym.EQUAL); }
    "print"            { return symbol(sym.PRINT); }
    "goto"            { return symbol(sym.GOTO); }
    "read"             { return symbol(sym.READ); }
    "if"               { return symbol(sym.IF); }
    ">"                { return symbol(sym.GT); }
    "<"                { return symbol(sym.LT); }
    ">="               { return symbol(sym.GE); }
    "<="               { return symbol(sym.LE); }
    "=="               { return symbol(sym.EQ); }
    "!="               { return symbol(sym.NEQ); }
    {dec_int_lit}      { 
                         return symbol(sym.NUM, new Integer(yytext())); }
    {dec_int_id}       { 
                         return symbol(sym.ID, yytext() );}
    {WhiteSpace}       { /* just skip what was found, do nothing */ }   
}
[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
<<EOF>>                          { return symbol(sym.EOF); }
