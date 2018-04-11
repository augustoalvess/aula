public class FirstProject {
	public static void main(String[] args) {
		// Resultado de um percurso em pré-ordem, portanto o inverso da pré-ordem deverá
		// reconstruir a árvore.

		//BinaryTree tree = makeBinaryTreeByExp("* + 3 6 - 4 1");
		BinaryTree tree = makeBinaryTreeByExp("+ * + 3 6 - 4 1 5");
		//BinaryTree tree = makeBinaryTreeByExp("+ 6 5");
		preorder(tree.getRoot());
	}

	public static BinaryTree makeBinaryTreeByExp(String exp) {		
		String expElements[] = exp.split(" ");
		BinaryTree tree = new BinaryTree(expElements[0]);
		Node after = tree.getRoot();

		for (int x = 1; x < expElements.length; x++) {
			while (after.left != null && after.right != null) {
				after = after.parent;
			}

			if (after.left == null) {
				tree.addLeftChild(after, expElements[x]);
				if (!isNumber(expElements[x])) {
					after = after.left;
				}
			} else {
				tree.addRightChild(after, expElements[x]);
				if (!isNumber(expElements[x])) {
					after = after.right;
				}
			}
		}

		return tree;
	}

	public static void preorder(Node n) {
	    // 1. node
	    System.out.println(n.element);
	    // 2. left child
	    if (n.left != null) { System.out.print("parent " + n.element + ", left "); preorder(n.left); }
	    // 3. right child
	    if (n.right != null) { System.out.print("parent " + n.element + ", right "); preorder(n.right); }
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