import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagNode extends Node {
    private String tagName;
    private Node root;
    private boolean isSingle;

    public TagNode(Node root) {
        super(root);
        this.root = root;
    }

    public void setSingle( boolean isSingle) {
        this.isSingle = isSingle;
    }

    public boolean isSingle() {
        return this.isSingle;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName ) {
        this.tagName = tagName;
    }

    @Override
    public String toHtml() {
        String attrString = this.getAttrs().stream().collect(
                StringBuilder::new,
                (a,b)->a.append( " " + b.toHtml() + " "),
                (a,b)->a.append(b)
        ).toString();
        if( isSingle ) {
            return  "<" + tagName + attrString + "/>" + "\n";
        }
        String content = "<" + tagName + attrString + ">" + "\n";
        for(Node node : getChildNodes()) {
            if ( !(node instanceof AttrNode)) {
                content += node.toHtml() + "\n";
            }
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
