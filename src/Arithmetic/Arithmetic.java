package Arithmetic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic {

    private static Matcher pattern1( String expression) {
        Pattern pattern = Pattern.compile("^(?<left>(\\(\\d+[\\+\\-\\*\\/]\\d+\\))|(\\d+[\\*\\/]\\d+)|\\d+)(?<right>([\\+\\-\\*\\/]((\\(\\d+[\\+\\-\\*\\/]\\d+\\))|(\\d+[\\*\\/]\\d+)|\\d+))+)$");
        Matcher matcher = pattern.matcher(expression);
        return matcher;
    }

    private static Matcher pattern2(String expression) {
        Pattern pattern = Pattern.compile("^\\(?(?<left>\\d+)(?<right>([\\+\\-\\*\\/](\\d+)))\\)?$");
        Matcher matcher = pattern.matcher(expression);
        return matcher;
    }

    private static ArithmeticItemBase process( Matcher matcher) throws Exception {
        String leftExpression = matcher.group("left");
        String rightExpression = matcher.group("right");
        RightItems rightItems = RightItems.convert(rightExpression);
        ArithmeticItem arithmeticItem = new ArithmeticItem(gen(leftExpression),gen(rightItems.getExpression()),rightItems.getOp());
        return arithmeticItem;
    }

    private static boolean isLeaf( String val ) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(val);
        return matcher.find();
    }

    private static ArithmeticItemBase gen( String expression ) throws Exception {
        if(isLeaf(expression)) {
            return new ArithmeticItemLeaf(expression);
        }
        Matcher matcher = pattern1(expression);
        if( !matcher.find() ) {
            matcher = pattern2(expression);
            if( matcher.find()) {
               return process(matcher);
            } else {
                throw new Exception("表达式有误");
            }
        } else {
             return process( matcher);
        }
    }

    public static int compute( String expression) {
        try{
            ArithmeticItemBase arithmeticItemBase = gen(expression);
            return arithmeticItemBase.getResult();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -99999;
        }
    }
}
