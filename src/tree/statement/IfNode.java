/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.statement;

import java.util.List;

import codegeneration.ExpressionCode;
import codegeneration.LabelGenerator;
import tree.expression.ExpressionNode;
import tree.expression.IdNode;

/**
 *
 * @author Eduardo
 */
public class IfNode extends StatementNode {

    private final IdNode goTo;
    ExpressionNode condition;

    public IfNode(ExpressionNode b, IdNode l) {
        this.condition = b;
        this.goTo = l;
    }

    @Override
    public void evaluate() {

    }

    @Override
    public String generateCode() {
        String ifLabel = LabelGenerator.getInstance().generateLabel("if");
        String cond = condition.GenerateCode().getCode();
        String toReturn = ";" + ifLabel + "\n" + cond + goTo.name + "\n";
        return toReturn;
    }


}
