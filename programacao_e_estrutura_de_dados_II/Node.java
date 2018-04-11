public class Node
{
  public Node parent;
  public Node left;
  public Node right;
  public String element;

  public Node(String s) {
    //parent = left = right = null;
    element = s;
  }

  public boolean isRoot() {
    return parent == null;
  }

  public boolean isInternal() {
    return ! isExternal();
  }

  public boolean isExternal() {
    return (left == null) && (right == null);
  }

  public String toString() {
      return element;
  }
}
