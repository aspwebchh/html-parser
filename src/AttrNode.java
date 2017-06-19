
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
    public String toString() {
        return key + "=\"" + val + "\"";
    }
}
