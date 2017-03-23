package tree.statement;

import tree.expression.IdNode;

/**
 * Created by Gabriel Paz on 22-Mar-17.
 */
public class LabelNode extends StatementNode {

    public IdNode idNode;
    public LabelNode(IdNode l) {
        this.idNode = l;
    }
    @Override
    public void evaluate() {

    }
    @Override
    public String generateCode() {
        return idNode.name + ":\n";
    }
}
