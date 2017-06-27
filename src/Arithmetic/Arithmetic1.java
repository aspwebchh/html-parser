package Arithmetic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic1 {
    static ArrayList<String> items;
    static int i = 0;
    static String word;

    private static String nextWord() {
        if( i >= items.size()) {
            return null;
        }
        return items.get(i++);
    }

    private static boolean expr() {
        if( !term() ) {
            return false;
        } else {
            return exprTail();
        }
    }

    private static boolean exprTail() {
        if( word == null) {
            return true;
        }
        if(word.equals("+") || word.equals("-")) {
            word = nextWord();
            if( !term() ) {
                return false;
            } else {
                return exprTail();
            }
        }
        return true;
    }

    private static boolean term() {
        if(!factor()) {
            return false;
        } else {
            return termTail();
        }
    }

    private static boolean termTail() {
        if( word == null ) {
            return true;
        }
        if( word.equals("*") || word.equals("/") ) {
            word = nextWord();
            if(!factor()) {
                return false;
            } else {
                return termTail();
            }
        }
        return true;
    }

    private static boolean factor() {
        if( word.equals( "(" ) ) {
            word = nextWord();
            if(!expr()) {
                return false;
            } else if( !word.equals( ")" ) ) {
                return false;
            }
            word = nextWord();
            return true;
        } else if( isInteger( word ) ) {
            word = nextWord();
            return true;
        }
        return false;
    }

    private static boolean isInteger( String val ) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(val);
        return matcher.find();
    }

    public static boolean compute(String expression) {
        items = new ArrayList<String>();
        items.add("(");
        items.add("2");
        items.add("*");
        items.add("(");
        items.add("2");
        items.add("+");
        items.add("91");
        items.add(")");
        items.add("+");
        items.add("11");
        items.add(")");
        items.add("*");
        items.add("2");

        System.out.println(items);

        word = nextWord();

        if( expr() ) {
            String nw = nextWord();
            if( nw == null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
