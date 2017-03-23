package tree.statement;

import tree.expression.IdNode;

/**
 * Created by Gabriel Paz on 22-Mar-17.
 */
public class GoToLabel extends StatementNode {

    public IdNode iDNode;

    public GoToLabel(IdNode l) {
        this.iDNode = l;
    }

    @Override
    public void evaluate() {

    }

    @Override
    public String generateCode()
    {
        return "jmp " + iDNode.name + "\n";
    }
}
