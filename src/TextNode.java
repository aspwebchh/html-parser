
public class TextNode extends Node{

    private String text;
    private Node root;

    public TextNode(Node root) {
        super(root);
        this.root = root;
        this.close();
    }

    public void setText(String text ) {
        this.text  = text;
    }

    public String getText() {
        return text;
    }

    public String toHtml() {
        return text;
    }

    public String toJSON() {
        return  "{\"text\":\"" + Common.replaceLetter( this.text ) + "\"}";
    }
}
