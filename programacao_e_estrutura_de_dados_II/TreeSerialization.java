public class TreeSerialization
{
  public static String serialize(Node n) {
      String s = "[\"" + n.element + "\", ";
      if (n.left != null) { s += serialize(n.left); }
      else { s += "null"; }
      s += ", ";
      if (n.right != null) { s += serialize(n.right); }
      else { s += "null"; }
      return s + "]";
  }

  public static BinaryTree deserialize(String s) {
    System.out.println("\"" + s.substring(2, 4) + "\" is root\n");
    BinaryTree tree = new BinaryTree(s.substring(2, 4));
    deserialize_children(tree, tree.getRoot(), s.substring(7));
    return tree;
  }

  public static int deserialize_children(BinaryTree tree, Node node, String s) {
    int index = 0;

    System.out.println(s);
    if (s.charAt(index) == '[') { // has left child
        index += 2; // skip '["'
        System.out.println(s.substring(index, index + 2) + " is left child\n");
        tree.addLeftChild(node, s.substring(index, index + 2));
        index += 5; // skip 'xx", '
        index += deserialize_children(tree, node.left, s.substring(index));
    }
    else { // null found
        System.out.println("no left child\n");
        index += 4; // skip 'null'
    }

    index += 2; // skip ', '

    System.out.println(s.substring(index));
    if (s.charAt(index) == '[') { // has right child
        index += 2; // skip '["'
        System.out.println(s.substring(index, index + 2) + " is right child\n");
        tree.addRightChild(node, s.substring(index, index + 2));
        index += 5; // skip 'xx", '
        index += deserialize_children(tree, node.right, s.substring(index));
    }
    else { // null found
        System.out.println("no right child\n");
        index += 4; // skip 'null'
    }

    index += 1; // skip ']'
    return index;
  }

  public static void main(String[] args)
  {
    // ["am", ["ep", ["qp", null, null], ["nh", null, null]], ["vs", null, null]]

    BinaryTree tree = new BinaryTree("am");

    Node am = tree.getRoot();
    Node ep = tree.addLeftChild( am, "ep");
    Node qp = tree.addLeftChild( ep, "qp");
    Node nh = tree.addRightChild(ep, "nh");
    Node vs = tree.addRightChild(am, "vs");

    String s = serialize(tree.getRoot());
    System.out.println(s);

    BinaryTree copy = deserialize(s);
    System.out.println(serialize(copy.getRoot()));
  }
}













