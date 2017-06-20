import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {
        RootNode root = RootNode.create("C:\\dev\\html_parser_for_java\\data.html");
        dump(root);
    }

    public static void dump(Node root) {
        System.out.println(root.toString());
    }
}
