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

    public MyTree subSet(int from, int to){
        /* TODO:
            1. Идём по дереву от корня. Если значение ноды:
            - за верхней границей, то идём в левую ветку
                (где значение меньше и могут попасть в диапазон; вправо не идём, там значения ещё выше).
            - до нижней границы, то идём в правую ветку
                (где значения больше и могут попасть в диапазон; влево не идём, там значения ещё ниже).
            - если значение в пределах границы, то мы добавляем его в новое дерево-результат.
                затем идём в левую ветку и в правую ветку.
            2. рекурсивнный вызов на каждом переходе в ветку. Возвращаем новое дерево.
         */
        return subSetRecursive(new MyTree(), root, from, to);
    }

    private MyTree subSetRecursive(MyTree tree, Node node, int from, int to){
        if (node == null) return tree;

        if (node.value >= to){
            subSetRecursive(tree, node.left, from, to);
        }
        else if (node.value < from){
            subSetRecursive(tree, node.right, from, to);
        }
        else {
            tree.add(node.value);
            subSetRecursive(tree, node.right, from, to);
            subSetRecursive(tree, node.left, from, to);
        }
        return tree;
    }

    public void trim(int start, int end){
        /* TODO:
            1. Передаём ноду (начиная с корня) и границы. Если значение ноды:
                - внутри границ, то идём в левую и в правую ветки.
                - если нода меньше границ, то удаляем левую ветку (там значения ещё меньше границы),
                    правую привязываем на её место и идём по ней.
                - если нода больше границ, то удаляем правую ветку (там значения ещё больше границы),
                    левую ветку привязываем но её место и идём по ней.
                - если нода внутри границ, то идём по правой и левой ветке.
             2. Если нода null, то и возвращаем null;
         */
        root = trimRecursive(root, start, end);
    }

    private Node trimRecursive(Node node, int start, int end) {
        if (node == null) return null;

        if (node.value < start) {
            node.left = null;
            node = trimRecursive(node.right, start, end);
        }
        else if (node.value >= end) {
            node.right = null;
            node = trimRecursive(node.left, start, end);
        }
        else {
            node.left = trimRecursive(node.left, start, end);
            node.right = trimRecursive(node.right, start, end);
        }
        return node;
    }
}
