public class BinaryTree
{
  private Node root;

  public BinaryTree(String s) {
    root = new Node(s);
  }

  public Node getRoot() { return root; }

  public Node addLeftChild(Node p, String s) {
    if (p.left == null) {
      Node n = new Node(s);
      p.left = n;
      n.parent = p;
      return n;
    }
    else {
      System.err.println("left child exists " + p.left);
      return null;
    }
  }

  public Node addRightChild(Node p, String s) {
    if (p.right == null) {
      Node n = new Node(s);
      p.right = n;
      n.parent = p;
      return n;
    }
    else {
      System.err.println("right child exists " + p.right);
      return null;
    }
  }

  public int depth(Node n) {
    int d = 0;
    while (n.parent != null) {
      n = n.parent;
      d++;
    }
    return d;
  }

  public int height(Node n) {
    if (n.isExternal()) {
      return 0;
    }
    else {
      int l = 0, r = 0;
      if (n.left != null) {
        l = height(n.left);
      }
      if (n.right != null) {
        r = height(n.right);
      }
      return Math.max(l, r) + 1;
    }
  }
}


























