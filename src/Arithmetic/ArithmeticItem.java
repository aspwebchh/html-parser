package Arithmetic;

/**
 * Created by 宏鸿 on 2017/6/26.
 */
public class ArithmeticItem extends ArithmeticItemBase{
    private ArithmeticItemBase left;
    private ArithmeticItemBase right;
    private String op;

    public ArithmeticItem( ArithmeticItemBase left, ArithmeticItemBase right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public int getResult() {
        int result = this.left.getResult();
        switch (op) {
            case "+":
                result += this.right.getResult();
                break;
            case "-":
                result -= this.right.getResult();
                break;
            case "*":
                result *= this.right.getResult();
                break;
            case "/":
                result /= this.right.getResult();
                break;
        }
        return result;
    }
}
