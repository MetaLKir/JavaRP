package treeset;

public class MyTreeAppl {
    public static void main(String[] args) {
        MyTree tree = new MyTree();
        tree.add(10);
        tree.add(15);
        tree.add(2);
        tree.add(19);
        tree.add(25);
        tree.add(39);
        tree.add(1);
        tree.add(4);
        tree.add(11);
        tree.add(16);
        tree.add(20);
        tree.add(7);
        tree.add(0);
        tree.add(17);
        tree.traverse();

        System.out.println(tree.contains(4));
        System.out.println(tree.contains(3));

        tree.remove(4);
        tree.traverse();

        // =========== HOMEWORK =============
        // method .subTree
        System.out.println("=".repeat(20) + " Homework subTree of myTree " + "=".repeat(20));
        System.out.println("===== My solution =====");
        tree.traverse();
        MyTree subTree = tree.subSet(6, 25);
        subTree.traverse();
        tree.traverse();
        System.out.println("===== Lector's solution =====");
        tree.traverse();
        subTree = tree.subSet2(6, 25);
        subTree.traverse();
        tree.traverse();

        // method .trim
        System.out.println("=".repeat(20) + " Homework trim myTree " + "=".repeat(20));
        System.out.println("===== My solution =====");
        subTree = tree.subSet(0, 50); // copy tree, to not break source tree
        subTree.traverse();
        subTree.trim(4, 16);
        subTree.traverse();
        System.out.println("===== Lector's solution =====");
        subTree = tree.subSet2(0, 50);
        subTree.traverse();
        subTree.trim2(4, 16);
        subTree.traverse();
    }
}
