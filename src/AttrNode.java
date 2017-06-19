
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
        String result = key;
        if (val != null) {
            result += "=\"" + val + "\"";
        }
        return result;
    }
}
