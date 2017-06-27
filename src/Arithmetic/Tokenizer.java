package Arithmetic;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    public static List<String> tokenizer(String expression) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(|\\)|\\d+|[\\+\\-\\*\\/]");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}
