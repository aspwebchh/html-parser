import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttrNode extends Node {
    private String key;
    private String val;

    public AttrNode(Node root) {
        super(root);
    }

    public void setAttr(String key, String val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toHtml() {
        String result = key;
        if (val != null) {
            result += "=\"" + val + "\"";
        }
        return result;
    }
}
