import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RootNode extends Node{

    public RootNode() {
        super(null);
    }

    @Override
    public String toHtml() {
        String content = "";
        for(Node node : getChildNodes()) {
            content += node.toHtml();
        }
        return content;
    }

    public static boolean canClose( Node currNode, String tagName ) {
        Node testCurrNode = currNode;
        boolean isContinue = true;
        isContinue = testCurrNode instanceof RootNode ? false : true;
        while( isContinue && !((TagNode)testCurrNode).getTagName().equals(tagName)) {
            testCurrNode = testCurrNode.getParentNode();
            if ( testCurrNode == null || testCurrNode instanceof RootNode) {
                isContinue = false;
                break;
            }
        }
        return isContinue;
    }

    private static void processAttr( RootNode root, TagNode tagNode, String htmlTag ) {
        Pattern pattern = Pattern.compile("\\s+(?<key>[^\\s=>\\/]+)((\\s*?=\\s*?)(\"|')(?<val>.+?)(\\4))?");
        Matcher matcher = pattern.matcher(htmlTag);
        while(matcher.find()) {
            AttrNode attrNode = new AttrNode(root);
            String key = matcher.group("key");
            String val = matcher.group("val");
            attrNode.setAttr( key, val );
            tagNode.addAttr(attrNode);
        }
    }

    private static boolean isClosedTag( String htmlTag ) {
        Pattern pattern = Pattern.compile("\\/\\s*>$");
        Matcher matcher = pattern.matcher(htmlTag);
        return matcher.find();
    }

    public static RootNode create( String path ) {
        String html = ReadFile.read(path);
        Pattern startTag = Pattern.compile("^<(?!\\s*\\/)[^>]+>");
        Pattern endTag = Pattern.compile("^<(?=\\s*\\/)[^>]+>");
        Pattern text = Pattern.compile("^[^<]+(?=<)");
        RootNode root = new RootNode();
        Node currNode = root;
        while(true) {
            Matcher startTagMatch = startTag.matcher(html);
            if( startTagMatch.find() ) {
                String htmlTag = startTagMatch.group();
                String tagName = TagNode.extractTagName(htmlTag);
                if(tagName.toUpperCase().equals("!DOCTYPE")) {
                    DocTypeNode docTypeNode = new DocTypeNode(root, htmlTag);
                    currNode.addChild(docTypeNode);
                } else {
                    TagNode tagNode = new TagNode(root);
                    tagNode.setTagName(tagName);
                    currNode.addChild(tagNode);
                    processAttr(root, tagNode,htmlTag);
                    if(isClosedTag(htmlTag)) {
                        tagNode.setSingle(true);
                    } else {
                        currNode = tagNode;
                    }
                }
                html = startTagMatch.replaceAll("");
            } else {
                Matcher endTagMatch = endTag.matcher(html);
                if( endTagMatch.find()) {
                    String tagName = TagNode.extractTagName(endTagMatch.group());
                    if( !canClose(currNode, tagName)) {
                        html = endTagMatch.replaceAll("");
                        continue;
                    }
                    while(!((TagNode)currNode).getTagName().equals(tagName)) {
                        currNode.getParentNode().addChilds(currNode.getChildNodes());
                        currNode.clearChildNodes();
                        currNode = currNode.getParentNode();
                    }
                    currNode.close();
                    currNode = currNode.getParentNode();
                    html = endTagMatch.replaceAll("");
                }  else {
                    Matcher textMatch = text.matcher(html);
                    if(textMatch.find()) {
                        TextNode textNode = new TextNode(root);
                        textNode.setText(textMatch.group());
                        currNode.addChild(textNode);
                        html = textMatch.replaceAll("");
                    } else {
                        break;
                    }
                }
            }
        }
        return root;
    }

}
