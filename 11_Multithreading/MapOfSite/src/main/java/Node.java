import java.util.Set;

public class Node {

    private String url;
    private Set<Node> children;

    public Node() {
    }

    public Node(String url, Set<Node> children) {
        this.url = url;
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Node> getChildren() {
        return children;
    }

    public void setChildren(Set<Node> children) {
        this.children = children;
    }

}