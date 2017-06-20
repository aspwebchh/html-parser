
public class DocTypeNode extends Node{
    private String htmlContent;

    public DocTypeNode(RootNode rootNode, String htmlContent) {
        super(rootNode);
        this.htmlContent = htmlContent;
    }

    public String toHtml() {
        return htmlContent + "\n";
    }
}
