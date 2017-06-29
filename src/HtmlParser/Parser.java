package HtmlParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static List<String> tokens;
    private static String token;
    private static int index;

    private static String nextToken() {
        if( index >= tokens.size() ) {
            return null;
        } else {
            return tokens.get(index++);
        }
    }

    private static boolean isTagStart() {
        if( token == null ) {
            return false;
        }
        Pattern pattern = Pattern.compile("^<\\s*[^> \\/]+([^>](?!\\/ *>))+>$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    private static boolean isTagEnd() {
        if( token == null ) {
            return false;
        }
        Pattern pattern = Pattern.compile("^<\\d*\\/[^>]+>$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    private static boolean isTagWithEnd() {
        if( token == null ) {
            return false;
        }
        Pattern pattern = Pattern.compile("^<[^>\\/]+\\/\\d*>$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    private static boolean isText() {
        if( token == null ) {
            return false;
        }
        return !isTagStart() && !isTagEnd() && !isTagWithEnd();
    }

    private static String getTagName() {
        Pattern pattern = Pattern.compile("[^<> \\/]+");
        Matcher matcher = pattern.matcher(token);
        if(matcher.find()){
            return matcher.group();
        } else {
            return null;
        }
    }

    private static boolean tags() {
        State val = tag();
        switch (val) {
            case Fail:
                return false;
            case End:
                return true;
            default:
                return tags();
        }
    }

    private static State tag() {
        if( isTagStart() ) {
            String startTagName = getTagName();
            token = nextToken();
            if( !tags() ){
                return State.Fail;
            }
            if( isTagEnd() ) {
                String endTagName = getTagName();
                if( !startTagName.equals(endTagName)) {
                    return State.Fail;
                }
                token = nextToken();
                return State.Success;
            }
            return State.Fail;
        } else {
            if( isText() ) {
                token = nextToken();
                return State.Success;
            }
            if( isTagWithEnd()) {
                token = nextToken();
                return State.Success;
            }
            return State.End;
        }
    }

     public static void parser( List<String> tokens ) {
         Parser.tokens = tokens;
         token = nextToken();
         if( tag() == State.Success ) {
             System.out.println("解析成功");
         } else {
             System.out.println("解析失败");
         }
         System.out.println(tokens);
    }
}
