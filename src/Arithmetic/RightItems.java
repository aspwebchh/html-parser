package Arithmetic;

public class RightItems {
    private String op;
    private String expression;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public static RightItems convert(String expression) {

        if( expression == null || expression.trim().equals("")) {
            return null;
        } else {
            RightItems rightItems = new RightItems();
            rightItems.setOp(expression.substring(0,1));
            rightItems.setExpression(expression.substring(1));
            return rightItems;
        }
    }
}
