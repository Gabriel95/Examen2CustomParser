
import java.io.*;
import java.util.HashMap;
import java.util.List;

import codegeneration.DataGeneration;
import codegeneration.VariableGenerator;
import tree.statement.StatementNode;

public class Main {

    static public void main(String argv[]) {
        /* Start the parser */
        try {
            parser p = new parser(new Lexer(new FileReader("src/test.txt")));
            List<StatementNode> result = (List<StatementNode>) p.parse();

            String code = "";

            for(StatementNode stmnt : result)
            {
                code+= stmnt.generateCode();
            }

            code = DataGeneration.GenerateDeclarations(VariableGenerator.getInstance().getVariables()) + code + DataGeneration.GenerateBottomTemplate();
            writeToFile("exam.asm",code);
        } catch (Exception e) {
            /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }
    }

    private static void writeToFile(String fileName, String toWrite)
    {
        try {

            File file = new File(fileName);

            if (file.createNewFile()){
                System.out.println("File was created!");
            }else{
                file.delete();
                file.createNewFile();
                System.out.println("File was created!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write(toWrite);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

}