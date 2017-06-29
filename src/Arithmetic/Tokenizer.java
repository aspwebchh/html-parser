package Arithmetic;


import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final int STATE_NULL = 0;
    private static final int STATE_INTEGER  = 1;
    private static final int STATE_OP = 2;
    private static final int STATE_BRACKETS = 3;

    public static List<String> tokenizer1(String expression) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(|\\)|\\d+|[\\+\\-\\*\\/]");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        System.out.println(result);
        return result;
    }

    public static List<String> tokenizer(String expression) {
        List<String> result = new ArrayList<>();
        String token = "";
        int state = STATE_NULL;
        int prevState = STATE_NULL;
        for (String item: expression.split("")) {
            if( isInteger(item)) {
               state = STATE_INTEGER;
            }
            if(isBrackets(item)) {
                state = STATE_BRACKETS;
            }
            if( isOpeate(item)) {
                state = STATE_OP;
            }
            if(prevState != state && prevState == STATE_INTEGER) {
                result.add(token);
                token = "";
            }
            if(state == STATE_INTEGER) {
                token += item;
            } else {
                token = item;
            }
            if( state != STATE_INTEGER ) {
                result.add(token);
                token = "";
            }
            prevState = state;
        }
        result.add(token);
        return result;
    }

    private static boolean isBrackets(String item) {
        return item.equals("(") || item.equals(")");
    }

    private static boolean isOpeate(String item) {
        return item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/");
    }

    private static boolean isInteger(String item) {
        return Pattern.matches("^\\d+$", item);
    }
}
