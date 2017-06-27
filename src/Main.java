import Arithmetic.Arithmetic1;

public class Main {


    public static void main(String[] args) {
//        RootNode root = RootNode.create("C:\\dev\\html_parser_for_java\\data1.html");
//        System.out.println(root.toJSON());
//        System.out.println(root.toHtml());
        boolean result = Arithmetic1.compute( "(2+91)*2" );
        System.out.println(result);
    }
}
