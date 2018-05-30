/**
 * @author HANGKANG
 * @date 2018/5/21 上午10:35
 */

public class Node {
    private boolean color;//true-->red   false-->black
    private Object key;
    private Node left;
    private Node right;
    private Node parent;

    public Node() {
    }

    public Node(boolean color, Object key, Node left, Node right, Node parent) {
        this.color = color;
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "color=" + color +
                ", key=" + key +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}
