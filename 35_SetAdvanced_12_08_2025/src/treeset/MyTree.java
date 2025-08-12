package treeset;

public class MyTree {

	private static class Node {
		private int value;
		private Node left;
		private Node right;

		public Node(int value) {
			this.value = value;
		}

	}

	private Node root;

	public void add(int value) {
		root = addRecursive(root, value);
	}

	private Node addRecursive(Node root, int value) {
		if (root == null)
			return new Node(value);
		if (value < root.value) {
			root.left = addRecursive(root.left, value);
		} else if (value > root.value) {
			root.right = addRecursive(root.right, value);
		}
		return root;
	}

	public void traverse() {
		traverseRecursive(root);
		System.out.println();
	}

	private void traverseRecursive(Node root) {
		if (root != null) {
			traverseRecursive(root.left);
			System.out.print(root.value + " ");
			traverseRecursive(root.right);
		}
	}

	public boolean contains(int value) {
		return searchRecursive(root, value) != null;
	}

	private Node searchRecursive(Node root, int value) {
		if (root == null || root.value == value)
			return root;
		if (value < root.value)
			return searchRecursive(root.left, value);
		return searchRecursive(root.right, value);
	}
}
