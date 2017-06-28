import Arithmetic.Arithmetic1;
import HtmlParser.Parser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static void htmlParser1() {
        RootNode root = RootNode.create("C:\\dev\\html_parser_for_java\\data1.html");
        System.out.println(root.toJSON());
        System.out.println(root.toHtml());
    }

    private static void arithmetic() {
        boolean result = Arithmetic1.compute( "(1+(2+91)/123)*2" );
        System.out.println(result);
    }

    private static void htmlParser2() {
        List<String> tokens = new ArrayList<>();
        tokens.add("<div>");
        tokens.add("<p>");
        tokens.add("content");
        tokens.add("</p>");
        tokens.add("<span>");
        tokens.add("<p>");
        tokens.add("<p>");
        tokens.add("<p>");
        tokens.add("content");
        tokens.add("</p>");
        tokens.add("</p>");
        tokens.add("</p>");
        tokens.add("</span>");
        //tokens.add("<input type='text'>");
        //tokens.add("这是一个文本节点");
        tokens.add("</div>");
        Parser.parser(tokens);
    }

    public static void main(String[] args) {
        //htmlParser1();
        //arithmetic();
        htmlParser2();
    }
}
