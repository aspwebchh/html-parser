import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
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
        Pattern pattern = Pattern.compile("\\s+(?<key>[^\\s=]+)((\\s*?=\\s*?)(\"|')(?<val>.+?)(\\4))?");
        Matcher matcher = pattern.matcher(htmlTag);
        while(matcher.find()) {
            AttrNode attrNode = new AttrNode(root);
            String key = matcher.group("key");
            String val = matcher.group("val");
            attrNode.setAttr( key, val );
            tagNode.addAttr(attrNode);
            //System.out.println(matcher.group("key") + "="+ matcher.group("val"));
        }
    }

    public static void main(String[] args) {
        String html = ReadFile.read("C:\\dev\\html_parser_for_java\\data1.html");
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
                    currNode = tagNode;
                    processAttr(root, tagNode,htmlTag);
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
       dump(root);
    }

    public static void dump(Node root) {
        System.out.println(root.toString());
    }
}
