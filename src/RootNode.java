
public class RootNode extends Node{

    public RootNode() {
        super(null);
    }

    @Override
    public String toString() {
        String content = "";
        for(Node node : getChildNodes()) {
            content += node.toString();
        }
        return content;
    }
}
