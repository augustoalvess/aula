public class FirstProject {
	public static void main(String[] args) {
		// Resultado de um percurso em pré-ordem, portanto o inverso da pré-ordem deverá
		// reconstruir a árvore.

		BinaryTree tree = makeBinaryTreeByExp("* + 3 6 - 4 1");
		//BinaryTree tree = makeBinaryTreeByExp("+ * + 3 6 - 4 1 5");
		//BinaryTree tree = makeBinaryTreeByExp("+ 6 5");
		preorder(tree.getRoot());
	}

	public static BinaryTree makeBinaryTreeByExp(String exp) {		
		String expElements[] = exp.split(" ");
		BinaryTree tree = new BinaryTree(expElements[0]);
		Node n;

		for (int x = 1; x < expElements.length; x++) {
			n = getFatherNode(tree.getRoot(), expElements[x]);
			if (n.left == null) {
				tree.addLeftChild(n, expElements[x]);
			} else {
				tree.addRightChild(n, expElements[x]);
			}
		}

		return tree;
	}

	public static Node getFatherNode(Node n, String s) {
		Node left, right;

		if (n.left != null) {
			left = getFatherNode(n.left, s);
			System.out.println("left" + left);
		}

		if (n.right != null) {
			right = getFatherNode(n.right, s);
			System.out.println("right" + right);
		}


		
		

		return n;
	}

	public static void preorder(Node n) {
	    // 1. node
	    System.out.println(n.element);
	    // 2. left child
	    if (n.left != null) { System.out.print("left "); preorder(n.left); }
	    // 3. right child
	    if (n.right != null) { System.out.print("right "); preorder(n.right); }
    }

    public static boolean isNumber(String s) {
    	try {
		    Double value = (Double.parseDouble(s));
	        return true;
		} catch (NumberFormatException e) {	  
	        return false;
		}
    }
}