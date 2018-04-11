public class TreeSearch
{
  public static void preorder(Node n) {
    // 1. node
    System.out.println(n.element);
    // 2. left child
    if (n.left != null) { preorder(n.left); }
    // 3. right child
    if (n.right != null) { preorder(n.right); }
  }

  public static void inorder(Node n) {
    // 1. left child
    if (n.left != null) { inorder(n.left); }
    // 2. node
    System.out.println(n.element);
    // 3. right child
    if (n.right != null) { inorder(n.right); }
  }

  public static void postorder(Node n) {
    // 1. left child
    if (n.left != null) { postorder(n.left); }
    // 2. right child
    if (n.right != null) { postorder(n.right); }
    // 3. node
    System.out.println(n.element);
  }

  public static int total(Node n) {
    int t = 0;
    if (n.left  != null) { t += total(n.left); }
    if (n.right != null) { t += total(n.right); }
    t += Integer.parseInt(n.element);
    System.out.println(t);
    return t;
  }

  public static void main(String[] args)
  {
    BinaryTree tree = new BinaryTree("1");

    Node n1 = tree.getRoot();
    Node n2 = tree.addLeftChild( n1, "2");
    Node n3 = tree.addRightChild(n1, "3");
    Node n4 = tree.addLeftChild( n2, "4");
    Node n5 = tree.addRightChild(n2, "5");
    Node n6 = tree.addLeftChild( n3, "6");
    Node n7 = tree.addRightChild(n3, "7");

    total(n1);
  }
}

