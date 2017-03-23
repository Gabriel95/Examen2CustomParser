/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.expression;


import codegeneration.ExpressionCode;
import codegeneration.VariableDeclaration;
import codegeneration.VariableGenerator;

import java.util.List;



/**
 *
 * @author Eduardo
 */
public class IdNode extends ExpressionNode{


    public IdNode(String name) {
        this.name = name;
    }

    public String name;


    @Override
    public float evaluate() {
        return 0;
    }

    @Override
    public ExpressionCode GenerateCode() {

        String nom = "@" + name + "@";
        if(!VariableGenerator.getInstance().checkIfVariableExist(nom))
        {
            VariableGenerator.getInstance().declareIntVariable(nom);
        }
        return new ExpressionCode("","["+nom+"]");
    }

    public void setValue(float evaluate) {

    }
    
    
}
