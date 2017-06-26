package Arithmetic;

public class ArithmeticItemLeaf extends ArithmeticItemBase{
    String val;
    public ArithmeticItemLeaf(String item) {
        this.val = item;
    }
    @Override
    public int getResult() {
        return Integer.parseInt(this.val);
    }
}
