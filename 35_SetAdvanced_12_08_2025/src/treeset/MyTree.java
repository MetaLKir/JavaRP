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

    private int findSmall(Node node) {
        return node.left == null ? node.value : findSmall(node.left);
    }

    public void remove(int value) {
        root = removeRecursive(root, value);
        // reassign root to root with removed element (which contained passed value)
    }

    private Node removeRecursive(Node root, int value) {
        if (root == null) return null;
        if (root.value == value) { // here removal logic
            // no children
            if (root.left == null && root.right == null) return null;
            // one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            //two children
            int small = findSmall(root.right);
            root.value = small;
            root.right = removeRecursive(root.right, small);
        } else if (value < root.value) // here and below logic going deeper through tree
            root.left = removeRecursive(root.left, value);
        else root.right = removeRecursive(root.right, value);

        return root;
    }
}
