public class BinarySearchTree
{
  private BinaryTree tree;

  public BinarySearchTree(String s) {
    this.tree = new BinaryTree(s);
  }
  
  public BinarySearchTree(Node root) {
    this.tree = new BinaryTree(root);
  }

  public boolean contains(String s) {
    return find(s) != null;
  }
  
  public Node find(String s) {
    Node n = this.tree.getRoot();
    while (n != null) {
      if (n.element.equals(s)) {
        return n;
      }
      if (n.element.compareTo(s) > 0) {
        n = n.left;
      }
      else {
        n = n.right;
      }
    }
    return null;
  }

  public Node insert(Node n, String s) {
    if (n==null) {
        n = new Node(s);
    } else if (s.compareTo(n.element) <= 0) {
        n.left = insert(n.left, s);
    } else {
        n.right = insert(n.right, s);
    }

    return n;
  }

  public String toString() {
    return this.tree.toString();
  }
}

