import Arithmetic.Arithmetic1;
import HtmlParser.Parser;
import HtmlParser.Tokenizer;

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
        List<String> tokens = Tokenizer.tokenizer("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/html\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>标\"题</div>\n" +
                "<div>内容</div>\n" +
                "</body>\n" +
                "</html>");
        Parser.parser(tokens);
    }

    public static void main(String[] args) {
        arithmetic();
        //htmlParser2();
    }
}
