import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagNode extends Node {
    private String tagName;
    private Node root;

    public TagNode(Node root) {
        super(root);
        this.root = root;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName ) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        String content = "<" + tagName + ">" + "\n";
        for(Node node : getChildNodes()) {
            content += node.toString() + "\n";
        }
        if( isClosed()) {
            content += "</" + tagName + ">";
        }
        return content;
    }

    public static String extractTagName( String htmlTag ) {
        Pattern pattern = Pattern.compile("[^<>\\/\\s]+");
        Matcher matcher = pattern.matcher(htmlTag);
        if( matcher.find()) {
            return matcher.group();
        } else {
            return "";
        }
    }
}
