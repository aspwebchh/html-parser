package HtmlParser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private static List<String> result = new ArrayList<>();

    public static List<String> tokenizer(String html) {
        String[] items = html.split("");
        String token = "";
        boolean isTag = false;
        boolean isText = false;
        for ( String item: items ) {
            if( item.equals( "<" ) ) {
                if( !token.equals("")) {
                    result.add(token);
                }
                token = "";
                isTag = true;
                isText = false;
            }
            if( isTag ) {
                token += item;
                if( item.equals(">")) {
                    isTag = false;
                    isText = true;
                    result.add(token);
                    token = "";
                }
                continue;
            }
            if( isText) {
                token += item;
            }
        }
        return result;
    }
}
