import javax.script.*;

/**
 * Autor: Augusto Alves da Silva - 565568
 */
public class FirstProject {
	public static void main(String[] args) {
		BinaryTree tree1 = makeBinaryTreeByExp("/ + 39 54 + 13 78");
		//preorder(tree1.getRoot());
		System.out.println("EXPRESSION #1 = " + result(tree1.getRoot()));
		System.out.println();

		BinaryTree tree2 = makeBinaryTreeByExp("* * 06 - 78 71 - - 94 26 * 55 29");
		//preorder(tree2.getRoot());
		System.out.println("EXPRESSION #2 = " + result(tree2.getRoot()));
		System.out.println();

		BinaryTree tree3 = makeBinaryTreeByExp("/ - - * 63 83 * - 22 46 51 / * 50 * 44 76 + - 02 11 - 70 21 / + * 05 / 23 90 + 81 * 40 65 / / - 77 33 * 08 24 + * 32 07 / 84 19");
		//preorder(tree3.getRoot());
		System.out.println("EXPRESSION #3 = " + result(tree3.getRoot()));
		System.out.println();
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
	    System.out.println(n.element);
	    if (n.left != null) { System.out.print("parent " + n.element + ", left "); preorder(n.left); }
	    if (n.right != null) { System.out.print("parent " + n.element + ", right "); preorder(n.right); }
    }

    public static Double result(Node n) {
    	Double result = 0.0;
    	Double leftResult = 0.0;
    	Double rightResult = 0.0;
    	if (n.left != null) { leftResult = result(n.left); }
    	if (n.right != null) { rightResult = result(n.right); }

		if (isNumber(n.element)) {
    		return Double.parseDouble(n.element);
    	} else {
    		if (n.element.equals("/") && rightResult == 0.0) {
    			rightResult = 1.0;
    		}
    		try {
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("js");
				String exp = "(" + leftResult + ")" + n.element + "(" + rightResult + ")";
				System.out.println(exp);
				result = Double.parseDouble(engine.eval(exp) + "");
			} catch (Exception err) {
				System.out.println(err.getMessage());
			}
    	}

    	return result;
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