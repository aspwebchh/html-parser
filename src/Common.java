import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    public static String replaceLetter( String content ) {
        if( content == null ) {
            return "";
        }
        content = content.replaceAll("\"","\\\\\"");
        return content;
    }

    private static boolean isChineseLetter(char item) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(""+item);
        return matcher.find();
    }

    public static String string2Unicode(String string) {
        if(string == null) {
            return "";
        }
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            if(!isChineseLetter(c)) {
                unicode.append(""+c);
            } else {
                // 转换为unicode
                unicode.append("\\u" + Integer.toHexString(c));
            }
        }

        return unicode.toString();
    }

    public static String unicode2String(String unicode) {
        if(unicode == null) {
            return "";
        }
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
