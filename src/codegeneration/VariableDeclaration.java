package codegeneration;

/**
 * Created by mac on 11/24/14.
 */
public class VariableDeclaration
{
    String name;
    String declarationType;
    String value;

    public VariableDeclaration(String name, String declarationType, String value) {
        this.name = name;
        this.declarationType = declarationType;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public String getDeclarationType() {
        return declarationType;
    }


    public String getValue() {
        return value;
    }

}
