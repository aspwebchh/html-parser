import java.util.ArrayList;
import java.util.List;

public abstract class Node implements Cloneable {
    private List<Node> childNodes = new ArrayList<>();
    private boolean closed = false;
    private Node root;

    public Node(Node root) {
        this.root = root;
    }

    public void close() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    private Node findParent( Node node, Node parent ) {
        Node result = null;
        for (Node item : parent.getChildNodes()) {
            if (node == item) {
                result = parent;
                break;
            } else {
                result = findParent(node, item);
            }
        }
        return result;
    }

    public Node getParentNode() {
        return findParent(this, root);
    }

    public void addChild( Node node ) {
        childNodes.add(node);
    }

    public void addChilds(List<Node> nodes) {
        childNodes.addAll(nodes);
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void clearChildNodes() {
        childNodes.clear();
    }
}
