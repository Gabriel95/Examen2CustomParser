public class sym
{
	public static final int EOF = 0;
	public static final int error = 1;
	public static final int PRINT = 2;
	public static final int GOTO = 3;
	public static final int LT = 4;
	public static final int MINUS = 5;
	public static final int ID = 6;
	public static final int IF = 7;
	public static final int GE = 8;
	public static final int MUL = 9;
	public static final int EQUAL = 10;
	public static final int NUM = 11;
	public static final int COLON = 12;
	public static final int EQ = 13;
	public static final int GT = 14;
	public static final int READ = 15;
	public static final int DIV = 16;
	public static final int LE = 17;
	public static final int NEQ = 18;
	public static final int PLUS = 19;
	public static final String[] terminalNames = new String[]
	{
		"$",
		"error",
		"PRINT",
		"GOTO",
		"LT",
		"MINUS",
		"ID",
		"IF",
		"GE",
		"MUL",
		"EQUAL",
		"NUM",
		"COLON",
		"EQ",
		"GT",
		"READ",
		"DIV",
		"LE",
		"NEQ",
		"PLUS"
	};
}