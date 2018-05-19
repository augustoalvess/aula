public class TestBinarySearchTree
{
  public static void main(String[] args)
  {
    // manually create an initial tree
    BinaryTree start = new BinaryTree("lemon");
    Node r = start.getRoot();
    start.addLeftChild(r, "apple");
    start.addRightChild(r, "strawberry");

    // create a binary search tree
    BinarySearchTree fruits = new BinarySearchTree(r);
    System.out.println("fruits:\n" + fruits + '\n');
    
    // test elements
    System.out.println(fruits.contains("lemon"));
    System.out.println(fruits.contains("apple"));
    System.out.println(fruits.contains("strawberry"));
    System.out.println(fruits.contains("orange"));

    fruits.insert(r, "banana");
    System.out.println(fruits);

    fruits.insert(r, "papaya");
    System.out.println(fruits);

    fruits.insert(r, "melon");
    System.out.println(fruits);

    fruits.insert(r, "pineapple");
    System.out.println(fruits);
  }
}

