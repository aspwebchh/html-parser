import Arithmetic.Arithmetic;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {


    public static void main(String[] args) {
//        RootNode root = RootNode.create("C:\\dev\\html_parser_for_java\\data1.html");
//        System.out.println(root.toJSON());
//        System.out.println(root.toHtml());
        int result =  Arithmetic.compute( "(1*(2+91))+1" );
        System.out.println(result);
    }
}
