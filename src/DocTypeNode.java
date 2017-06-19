
public class DocTypeNode extends Node{
    private String htmlContent;

    public DocTypeNode(RootNode rootNode, String htmlContent) {
        super(rootNode);
        this.htmlContent = htmlContent;
    }

    @Override
    public String toString() {
        return htmlContent + "\n";
    }
}
